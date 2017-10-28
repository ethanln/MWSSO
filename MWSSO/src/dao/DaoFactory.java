package dao;

import com.google.gson.internal.LinkedTreeMap;
import dao.idao.*;
import dao.ifactory.IDaoFactory;
import exception.DaoFactoryException;
import shared.util.ConfigurationManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ethan on 10/27/17.
 */
public class DaoFactory implements IDaoFactory {

    /**
     * Existing dao set registries.
     */
    private HashMap<String, String> registries;
    /**
     * Current registered dao set.
     */
    private IDaoFactory registeredDaoFactory;

    public DaoFactory(String registry) throws DaoFactoryException{
        this.registries = new HashMap<>();
        this.loadRegistryTypes();
        this.registerDaoFactory(registry);
    }

    private void loadRegistryTypes() throws DaoFactoryException{
        try {
            // Fetch the registry types from the configuration.
            ArrayList<LinkedTreeMap<String, String>> registries = (ArrayList<LinkedTreeMap<String, String>>) ConfigurationManager.fetchDaoSetRegistryConfiguration("registries");
            // Load all registry types in the registries hash structure.
            for (String registry : registries.get(0).keySet()) {
                this.registries.put(registry, registries.get(0).get(registry));
            }
        }
        catch(Exception ex)
        {
            throw new DaoFactoryException("Could not load dao set registries.");
        }
    }

    public void registerDaoFactory(String daoRegistry) throws DaoFactoryException{
        try{
            Class c = Class.forName(this.registries.get(daoRegistry));
            this.registeredDaoFactory = (IDaoFactory)c.newInstance();
        }
        catch(Exception e){
            throw new DaoFactoryException("Could not register dao set " + daoRegistry + ".");
        }
    }

    @Override
    public ISSOGroupPermissionDao createSSOGroupPermissionDao() throws DaoFactoryException{
        return this.registeredDaoFactory.createSSOGroupPermissionDao();
    }

    @Override
    public ISSOSessionTokenDao createSSOSessionTokenDao() throws DaoFactoryException{
        return this.registeredDaoFactory.createSSOSessionTokenDao();
    }

    @Override
    public ISSOUserDao createSSOUserDao() throws DaoFactoryException{
        return this.registeredDaoFactory.createSSOUserDao();
    }

    @Override
    public ISSOUserPermissionDao createSSOUserPermissionDao() throws DaoFactoryException{
        return this.registeredDaoFactory.createSSOUserPermissionDao();
    }

    @Override
    public ISSOUserRegistrationRequestDao createSSOUserRegistrationRequestDao() throws DaoFactoryException{
        return this.registeredDaoFactory.createSSOUserRegistrationRequestDao();
    }
}

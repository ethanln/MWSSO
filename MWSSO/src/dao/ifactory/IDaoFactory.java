package dao.ifactory;

import dao.idao.*;
import exception.DaoFactoryException;

/**
 * Created by ethan on 10/27/17.
 */
public interface IDaoFactory {

    /**
     * Returns new instance of group permission dao.
     * @return
     * @throws DaoFactoryException
     */
    ISSOGroupPermissionDao createSSOGroupPermissionDao() throws DaoFactoryException;

    /**
     * Returns new instance of session token dao.
     * @return
     * @throws DaoFactoryException
     */
    ISSOSessionTokenDao createSSOSessionTokenDao() throws DaoFactoryException;

    /**
     * Returns new instance of user dao.
     * @return
     * @throws DaoFactoryException
     */
    ISSOUserDao createSSOUserDao()throws DaoFactoryException;

    /**
     * Returns new instance of user permission dao.
     * @return
     * @throws DaoFactoryException
     */
    ISSOUserPermissionDao createSSOUserPermissionDao()throws DaoFactoryException;

    /**
     * Returns new instance of user registration request dao.
     * @return
     * @throws DaoFactoryException
     */
    ISSOUserRegistrationRequestDao createSSOUserRegistrationRequestDao()throws DaoFactoryException;
}

package dao;

import dao.idao.ISSOGroupPermissionDao;
import exception.DaoException;
import shared.model.SSOGroupPermission;

import java.util.List;

/**
 * Created by ethan on 8/20/17.
 */
public class SSOGroupPermissionDao extends Dao implements ISSOGroupPermissionDao{

    /**
     * Constructor.
     */
    public SSOGroupPermissionDao() throws DaoException{
        // TEST TO MAKE SURE THAT THE PARENT CONSTRUCTOR IS BEING INVOKED.
    }

    @Override
    public SSOGroupPermission get(int id) throws DaoException {
        return null;
    }

    @Override
    public SSOGroupPermission getByName(String name) throws DaoException {
        return null;
    }

    @Override
    public List<SSOGroupPermission> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(SSOGroupPermission ssoGroupPermission) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }
}
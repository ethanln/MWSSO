package dao;

import dao.idao.ISSOUserPermissionDao;
import exception.DaoException;
import shared.model.SSOUserPermission;

import java.util.List;

/**
 * Created by ethan on 8/20/17.
 */
public class SSOUserPermissionDao extends Dao implements ISSOUserPermissionDao {

    /**
     * Constructor.
     * @throws DaoException
     */
    public SSOUserPermissionDao() throws DaoException {

    }

    @Override
    public SSOUserPermission get(int id) throws DaoException {
        return null;
    }

    @Override
    public List<SSOUserPermission> getUserById(int userId) throws DaoException {
        return null;
    }

    @Override
    public List<SSOUserPermission> getByGroupId(int groupId) throws DaoException {
        return null;
    }

    @Override
    public List<SSOUserPermission> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(SSOUserPermission ssoUserPermission) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }
}

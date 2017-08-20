package dao;

import dao.idao.ISSOUserDao;
import exception.DaoException;
import shared.model.SSOUser;

import java.util.List;

/**
 * Created by ethan on 8/20/17.
 */
public class SSOUserDao extends Dao implements ISSOUserDao {

    /**
     * Constructor.
     */
    public SSOUserDao() throws DaoException{
        // TEST TO MAKE SURE THAT THE PARENT CONSTRUCTOR IS BEING INVOKED.
    }

    @Override
    public SSOUser get(int id) throws DaoException {
        return null;
    }

    @Override
    public SSOUser getByUsername(String username) throws DaoException {
        return null;
    }

    @Override
    public List<SSOUser> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(SSOUser ssoUser) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }
}

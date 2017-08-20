package dao;

import dao.idao.ISSOSessionTokenDao;
import dao.idao.ISSOUserDao;
import exception.DaoException;
import shared.model.SSOSessionToken;

import java.util.List;

/**
 * Created by ethan on 8/20/17.
 */
public class SSOSessionTokenDao extends Dao implements ISSOSessionTokenDao {

    private ISSOUserDao ssoUserDao;

    /**
     * Constructor.
     * @throws DaoException
     */
    public SSOSessionTokenDao() throws DaoException {
        // TEST TO MAKE SURE THAT THE PARENT CONSTRUCTOR IS BEING INVOKED.
    }

    @Override
    public SSOSessionToken get(int id) throws DaoException{
        return null;
    }

    @Override
    public SSOSessionToken getByToken(String token) throws DaoException{
        return null;
    }

    @Override
    public List<SSOSessionToken> getAll() throws DaoException{
        return null;
    }

    @Override
    public void update(SSOSessionToken ssoSessionToken) throws DaoException{

    }

    @Override
    public void delete(int id) throws DaoException{

    }
}

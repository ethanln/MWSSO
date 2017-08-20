package dao;

import dao.idao.ISSOUserRegistrationRequestDao;
import exception.DaoException;
import shared.model.SSOUserRegistrationRequest;

import java.util.List;

/**
 * Created by ethan on 8/20/17.
 */
public class SSOUserRegistrationRequestDao extends Dao implements ISSOUserRegistrationRequestDao {

    /**
     * Constructor.
     * @throws DaoException
     */
    public SSOUserRegistrationRequestDao() throws DaoException {
        // TEST TO MAKE SURE THAT THE PARENT CONSTRUCTOR IS BEING INVOKED.
    }

    @Override
    public SSOUserRegistrationRequest get(int id) throws DaoException {
        return null;
    }

    @Override
    public SSOUserRegistrationRequest getByToken(String token) throws DaoException {
        return null;
    }

    @Override
    public List<SSOUserRegistrationRequest> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(SSOUserRegistrationRequest ssoUserRegistrationRequest) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }
}

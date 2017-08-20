package dao.idao;

import exception.DaoException;
import shared.model.SSOUserRegistrationRequest;

import java.util.List;

/**
 * Created by ethan on 8/14/17.
 */
public interface ISSOUserRegistrationRequestDao {
    /**
     * Fetches SSO user registration request instance from database by id.
     * @param id
     * @return
     */
    SSOUserRegistrationRequest get(int id) throws DaoException;

    /**
     * Fetches SSO user registration request instance from database by token.
     * @param token
     * @return
     */
    SSOUserRegistrationRequest getByToken(String token) throws DaoException;

    /**
     * Fetches all SSO user registration request instances from database.
     * @return
     */
    List<SSOUserRegistrationRequest> getAll() throws DaoException;

    /**
     * Updates SSO user registration request record in database.
     * @param ssoUserRegistrationRequest
     */
    void update(SSOUserRegistrationRequest  ssoUserRegistrationRequest) throws DaoException;

    /**
     * Deletes SSO user registration request record from database.
     * @param id
     */
    void delete(int id) throws DaoException;
}

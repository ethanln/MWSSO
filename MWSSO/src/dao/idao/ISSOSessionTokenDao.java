package dao.idao;

import exception.DaoException;
import shared.model.SSOSessionToken;

import java.util.List;

/**
 * Created by ethan on 8/14/17.
 */
public interface ISSOSessionTokenDao {
    /**
     *Fetches SSO session token instance from database by id.
     * @param id
     * @return
     */
    SSOSessionToken get(int id) throws DaoException;

    /**
     * Fetches SSO session token instance from database by token.
     * @param token
     * @return
     */
    SSOSessionToken getByToken(String token) throws DaoException;

    /**
     * Fetches all SSO session token instances from database.
     * @return
     */
    List<SSOSessionToken> getAll() throws DaoException;

    /**
     * Updates SSO session token record in database.
     * @param ssoSessionToken
     */
    void update(SSOSessionToken ssoSessionToken) throws DaoException;

    /**
     * Deletes SSO session token record from database.
     * @param id
     */
    void delete (int id) throws DaoException;
}

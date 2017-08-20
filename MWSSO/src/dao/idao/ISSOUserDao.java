package dao.idao;

import exception.DaoException;
import shared.model.SSOUser;
import java.util.List;

/**
 * Created by ethan on 8/14/17.
 */
public interface ISSOUserDao {

    /**
     * Fetches SSO user instance from database by id.
     * @param id
     * @return
     */
    SSOUser get(int id) throws DaoException;

    /**
     * Fetches SSO user instance from database by username.
     * @param username
     * @return
     */
    SSOUser getByUsername(String username) throws DaoException;

    /**
     * Fetches all SSO user instances from database.
     * @return
     */
    List<SSOUser> getAll() throws DaoException;

    /**
     * Updates SSO user record in database.
     * @param ssoUser
     */
    void update(SSOUser ssoUser) throws DaoException;

    /**
     * Deletes SSO user record from database.
     * @param id
     */
    void delete(int id) throws DaoException;
}

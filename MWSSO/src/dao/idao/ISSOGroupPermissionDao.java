package dao.idao;

import exception.DaoException;
import shared.model.SSOGroupPermission;

import java.util.List;

/**
 * Created by ethan on 8/14/17.
 */
public interface ISSOGroupPermissionDao {

    /**
     * Fetches SSO group permission instance from database by id.
     * @param id
     * @return
     */
    SSOGroupPermission get(int id) throws DaoException;

    /**
     * Fetches SSO group permission instnace from database by name.
     * @param name
     * @return
     */
    SSOGroupPermission getByName(String name) throws DaoException;

    /**
     * Fetches all SSO group permission instances from database.
     * @return
     */
    List<SSOGroupPermission> getAll() throws DaoException;

    /**
     * Updates SSO group permission record in database.
     * @param ssoGroupPermission
     */
    void update(SSOGroupPermission ssoGroupPermission) throws DaoException;

    /**
     * Deletes SSO group permission record from database.
     * @param id
     */
    void delete(int id) throws DaoException;
}

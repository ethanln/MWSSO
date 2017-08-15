package dao.idao;

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
    SSOGroupPermission get(int id);

    /**
     * Fetches SSO group permission instnace from database by name.
     * @param name
     * @return
     */
    SSOGroupPermission getByName(String name);

    /**
     * Fetches all SSO group permission instances from database.
     * @return
     */
    List<SSOGroupPermission> getAll();

    /**
     * Updates SSO group permission record in database.
     * @param ssoGroupPermission
     */
    void update(SSOGroupPermission ssoGroupPermission);

    /**
     * Deletes SSO group permission record from database.
     * @param id
     */
    void delete(int id);
}

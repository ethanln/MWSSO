package dao.idao;

import shared.model.SSOUserPermission;

import java.util.List;

/**
 * Created by ethan on 8/14/17.
 */
public interface ISSOUserPermissionDao {
    /**
     * Fetches SSO user permission instance from database by id.
     * @param id
     * @return
     */
    SSOUserPermission get(int id );

    /**
     * Fetches SSO user permission instance from database by user id.
     * @param userId
     * @return
     */
    List<SSOUserPermission> getUserById(int userId);

    /**
     * Fetches SSO user permission instance from database by group id.
     * @param groupId
     * @return
     */
    List<SSOUserPermission> getByGroupId(int groupId);

    /**
     * Fetches all SSO user permission instances from database.
     * @return
     */
    List<SSOUserPermission> getAll();

    /**
     * Updates SSO user permission record in database.
     * @param ssoUserPermission
     */
    void update(SSOUserPermission ssoUserPermission);

    /**
     * Deletes SSO user permission record from database.
     * @param id
     */
    void delete(int id);
}

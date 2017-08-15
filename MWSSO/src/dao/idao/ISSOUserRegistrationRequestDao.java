package dao.idao;

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
    SSOUserRegistrationRequest get(int id);

    /**
     * Fetches SSO user registration request instance from database by token.
     * @param token
     * @return
     */
    SSOUserRegistrationRequest getByToken(String token);

    /**
     * Fetches all SSO user registration request instances from database.
     * @return
     */
    List<SSOUserRegistrationRequest> getAll();

    /**
     * Updates SSO user registration request record in database.
     * @param ssoUserRegistrationRequest
     */
    void update(SSOUserRegistrationRequest  ssoUserRegistrationRequest);

    /**
     * Deletes SSO user registration request record from database.
     * @param id
     */
    void delete(int id);
}

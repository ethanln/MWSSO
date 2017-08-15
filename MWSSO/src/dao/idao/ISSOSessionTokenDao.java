package dao.idao;

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
    SSOSessionToken get(int id);

    /**
     * Fetches SSO session token instance from database by token.
     * @param token
     * @return
     */
    SSOSessionToken getByToken(String token);

    /**
     * Fetches all SSO session token instances from database.
     * @return
     */
    List<SSOSessionToken> getAll();

    /**
     * Updates SSO session token record in database.
     * @param ssoSessionToken
     */
    void update(SSOSessionToken ssoSessionToken);

    /**
     * Deletes SSO session token record from database.
     * @param id
     */
    void delete (int id);
}

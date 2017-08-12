package shared.model;

/**
 * Created by ethan on 8/12/17.
 */
public class SSOUserPermission {
    /**
     * SSO User permission id.
     */
    public int id;
    /**
     * SSO User pointer instance.
     */
    public SSOUser ssoUser;
    /**
     * SSO group permission instance.
     */
    public SSOGroupPermission ssoGroupPermission;

    /**
     * Constructor.
     * @param _id
     * @param _ssoUser
     * @param _ssoGroupPermission
     */
    public SSOUserPermission(int _id, SSOUser _ssoUser, SSOGroupPermission _ssoGroupPermission){
        this.id = _id;
        this.ssoUser = _ssoUser;
        this.ssoGroupPermission = _ssoGroupPermission;
    }

    /**
     * Default constructor.
     */
    public SSOUserPermission(){
        this.id = -1;
        this.ssoUser = null;
        this.ssoGroupPermission = null;
    }
}

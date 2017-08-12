package shared.model;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by ethan on 8/12/17.
 */
public class SSOSessionToken {

    /**
     * SSO session token id.
     */
    public int id;
    /**
     * SSO session token value.
     */
    public String token;
    /**
     * SSO session token date of creation.
     */
    public Date dateCreated;
    /**
     * SSO session token expiration date.
     */
    public Date expirationDate;
    /**
     * SSO session token active flag.
     */
    public boolean isActive;
    /**
     * SSO session token user instance.
     */
    public SSOUser ssoUser;

    /**
     * Constructor.
     * @param _id
     * @param _token
     * @param _dateCreated
     * @param _expirationDate
     * @param _isActive
     * @param _ssoUser
     */
    public SSOSessionToken(int _id, String _token, Date _dateCreated, Date _expirationDate, boolean _isActive, SSOUser _ssoUser){
        this.id = _id;
        this.token = _token;
        this.dateCreated = _dateCreated;
        this.expirationDate = _expirationDate;
        this.isActive = _isActive;
        this.ssoUser = _ssoUser;
    }

    /**
     * Default Constructor.
     */
    public SSOSessionToken(){
        this.id = -1;
        this.token = null;
        this.dateCreated = new Date(Calendar.getInstance().getTime().getTime());
        this.expirationDate = new Date(Calendar.getInstance().getTime().getTime());
        this.isActive = false;
        this.ssoUser = null;
    }
}

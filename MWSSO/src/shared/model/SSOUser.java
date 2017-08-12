package shared.model;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by ethan on 7/16/17.
 */
public class SSOUser {
    /**
     * SSO user id.
     */
    public int id;
    /**
     * SSO user username.
     */
    public String username;
    /**
     * SSO user password.
     */
    public String password;
    /**
     * SSO user email.
     */
    public String email;
    /**
     * SSO user activity state.
     */
    public boolean isActive;
    /**
     * SSO user date when created.
     */
    public Date dateCreated;

    /**
     * Constructor.
     * @param _id
     * @param _username
     * @param _password
     * @param _email
     * @param _isActive
     * @param _dateCreated
     */
    public SSOUser(int _id, String _username, String _password, String _email, boolean _isActive, Date _dateCreated){
        this.id = _id;
        this.username = _username;
        this.password = _password;
        this.email = _email;
        this.isActive = _isActive;
        this.dateCreated = _dateCreated;
    }

    /**
     * Default constructor.
     */
    public SSOUser(){
        this.id = -1;
        this.username = null;
        this.password = null;
        this.email = null;
        this.isActive = false;
        this.dateCreated = new Date(Calendar.getInstance().getTime().getTime());
    }
}

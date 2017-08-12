package shared.model;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by ethan on 7/16/17.
 */
public class SSOUserRegistrationRequest {
    /**
     * SSO user registration id.
     */
    public int id;
    /**
     * SSO user registration username.
     */
    public String username;
    /**
     * SSO user registration password.
     */
    public String password;
    /**
     * SSO user registration email.
     */
    public String email;
    /**
     * SSO user registration accepted flag.
     */
    public boolean isAccepted;
    /**
     * SSO user registration date created.
     */
    public Date dateCreated;

    /**
     * Constructor.
     * @param _id
     * @param _username
     * @param _password
     * @param _email
     * @param _isAccepted
     * @param _dateCreated
     */
    public SSOUserRegistrationRequest(int _id, String _username, String _password, String _email, boolean _isAccepted, Date _dateCreated){
        this.id = _id;
        this.username = _username;
        this.password = _password;
        this.email = _email;
        this.isAccepted = _isAccepted;
        this.dateCreated = _dateCreated;
    }

    /**
     * Default constructor.
     */
    public SSOUserRegistrationRequest(){
        this.id = -1;
        this.username = null;
        this.password = null;
        this.email = null;
        this.isAccepted = false;
        this.dateCreated = new Date(Calendar.getInstance().getTime().getTime());
    }
}

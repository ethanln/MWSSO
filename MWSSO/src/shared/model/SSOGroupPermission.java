package shared.model;

/**
 * Created by ethan on 8/12/17.
 */
public class SSOGroupPermission {
    /**
     * SSO group permission id.
     */
    public int id;
    /**
     * SSO group permission name.
     */
    public String name;

    /**
     * Constructor.
     * @param _id
     * @param _name
     */
    public SSOGroupPermission(int _id, String _name){
        this.id = _id;
        this.name = _name;
    }

    /**
     * Default Constructor.
     */
    public SSOGroupPermission(){
        this.id = -1;
        this.name = null;
    }
}

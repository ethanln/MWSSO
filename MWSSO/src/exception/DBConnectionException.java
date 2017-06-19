package exception;

/**
 * Created by ethan on 6/18/17.
 */
public class DBConnectionException extends Exception {

    public DBConnectionException(String message){
        super(message);
    }

    public DBConnectionException() {}
}

package exception;

/**
 * Created by ethan on 10/27/17.
 */
public class DaoFactoryException extends Exception {
    public DaoFactoryException(String message){
        super(message);
    }

    public DaoFactoryException() {}
}

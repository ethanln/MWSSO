package dao;

import exception.DaoException;
import shared.util.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ethan on 6/22/17.
 */
public class Dao {

    private String connectionString;
    private Connection dbConn;

    public Dao() throws DaoException {
        try {
            this.init();
        }
        catch(DaoException ex){
            throw ex;
        }
    }

    /**
     * Initiates db connection parameters.
     * @throws DaoException
     */
    private void init() throws DaoException {
        String driver = (String)ConfigurationManager.fetchDbConfiguration("driver");
        String service = (String)ConfigurationManager.fetchDbConfiguration("service");
        String username = (String)ConfigurationManager.fetchDbConfiguration("username");
        String password = (String)ConfigurationManager.fetchDbConfiguration("password");
        String database = (String)ConfigurationManager.fetchDbConfiguration("database");
        String domain = (String)ConfigurationManager.fetchDbConfiguration("domain");
        String port = (String)ConfigurationManager.fetchDbConfiguration("port");

        // Register driver.
        try {
            this.registerDriver(driver);
        }
        catch(DaoException ex){
            throw ex;
        }
        // Build connection string.
        this.connectionString = "jdbc:" + service + "://" + domain + ":" + port + "/" +  database + "?user=" + username + "&password=" + password;
    }

    private void registerDriver(String driver) throws DaoException
    {
        try {
            // Register DB driver.
            Class.forName(driver).newInstance();
        }
        catch(ClassNotFoundException ex){
            throw new DaoException("Class Not Found - Could not register driver.");
        }
        catch(IllegalAccessException ex){
            throw new DaoException("Illegal Access - Could not register driver.");
        }
        catch(InstantiationException ex){
            throw new DaoException("Instantiation - Could not register driver.");
        }
    }

    /**
     * Opens a connection to a db server.
     * @throws DaoException
     */
    public void open() throws DaoException {
        try {
            // Create db connection instance.
            this.dbConn = DriverManager.getConnection(this.connectionString);
        }
        catch(SQLException ex){
            String message = "SQLException: " + ex.getMessage() + "\n" +
                            "SQLState: " + ex.getSQLState() + "\n" +
                            "VendorError: " + ex.getErrorCode();
            throw new DaoException(message);
        }
    }

    /**
     * Begins transaction to db server.
     * @throws DaoException
     */
    protected void begin() throws DaoException {
        throw new DaoException("Not yet implemented.");
    }

    /**
     * Executes a query and returns raw string data.
     * @param query
     * @return
     * @throws DaoException
     */
    protected String executeQuery(String query) throws DaoException {
        throw new DaoException("Not yet implemented.");
    }

    /**
     * Commits transaction to db server.
     * @throws DaoException
     */
    protected void commit() throws DaoException {
            throw new DaoException("Not yet implemented.");
    }

    /**
     * Cancels transaction to db server.
     * @throws DaoException
     */
    protected void cancel() throws DaoException {
        throw new DaoException("Not yet implemented.");
    }

    /**
     * Closes connection to db server.
     * @throws DaoException
     */
    protected void close() throws DaoException {
        throw new DaoException("Not yet implemented.");
    }
}

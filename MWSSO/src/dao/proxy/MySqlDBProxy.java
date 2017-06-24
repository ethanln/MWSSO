package dao.proxy;

import exception.DBProxyException;
import shared.util.ConfigurationManager;

import java.sql.*;

/**
 * Created by ethan on 6/22/17.
 */
public class MySqlDBProxy implements IDBProxy{

    /**
     * Connection string.
     */
    private String connectionString;

    /**
     * Connection instance.
     */
    private Connection dbConn;

    /**
     * Transaction state.
     */
    private boolean isTransaction;

    /**
     * Constructor.
     * @throws DBProxyException
     */
    public MySqlDBProxy() throws DBProxyException {
        this.isTransaction = false;
        try {
            this.init();
        }
        catch(DBProxyException ex){
            throw ex;
        }
    }

    /**
     * Initiates db connection parameters.
     * @throws DBProxyException
     */
    private void init() throws DBProxyException {
        String driver = (String)ConfigurationManager.fetchDbConfiguration("driver");
        String service = (String)ConfigurationManager.fetchDbConfiguration("service");
        String username = (String)ConfigurationManager.fetchDbConfiguration("username");
        String password = (String)ConfigurationManager.fetchDbConfiguration("password");
        String database = (String)ConfigurationManager.fetchDbConfiguration("database");
        String domain = (String)ConfigurationManager.fetchDbConfiguration("domain");
        String port = (String)ConfigurationManager.fetchDbConfiguration("port");

        try {
            // Register driver.
            this.registerDriver(driver);
            // Build connection string.
            this.connectionString = "jdbc:" + service + "://" + domain + ":" + port + "/" +  database + "?user=" + username + "&password=" + password;
        }
        catch(DBProxyException ex){
            throw ex;
        }
    }

    /**
     * Registers the used db driver.
     * @param driver
     * @throws DBProxyException
     */
    private void registerDriver(String driver) throws DBProxyException
    {
        try {
            // Register DB driver.
            Class.forName(driver).newInstance();
        }
        catch(ClassNotFoundException ex){
            // throw error if class is not found during reflection process.
            throw new DBProxyException("Class Not Found - Could not register driver.");
        }
        catch(IllegalAccessException ex){
            // throw error if there is illegal access during reflection process.
            throw new DBProxyException("Illegal Access - Could not register driver.");
        }
        catch(InstantiationException ex){
            // Throw error if there is an instantiation problem during the reflection process.
            throw new DBProxyException("Instantiation - Could not register driver.");
        }
    }

    private boolean isClosed(){
        try {
            return this.dbConn == null || this.dbConn.isClosed();
        }
        catch(SQLException ex){
            return true;
        }
    }

    @Override
    public void open() throws DBProxyException {
        // TEST
        try {
            // Create db connection instance.
            this.dbConn = DriverManager.getConnection(this.connectionString);
        }
        catch(SQLException ex){
            // Throw db connection error if connection fails.
            String message = "SQLException: " + ex.getMessage() + "\n" +
                             "SQLState: " + ex.getSQLState() + "\n" +
                             "VendorError: " + ex.getErrorCode();
            throw new DBProxyException(message);
        }
    }

    @Override
    public void begin() throws DBProxyException {
        // TEST
        try {
            if (this.isClosed()) {
                // Throw error if there is no db connection instance.
                throw new DBProxyException("No database connection available.");
            }

            // Begin a transaction.
            this.dbConn.setAutoCommit(false);
            // Initiate transaction state.
            this.isTransaction = true;
        }
        catch(SQLException ex){
            // Throw error if there is no db connection instance.
            throw new DBProxyException("No database connection available.");
        }
    }

    @Override
    public String executeFetchQuery(String query) throws DBProxyException {
        // FINISH IMPLEMENTING.
        if(this.isClosed()){
            // Throw error if there is no db connection instance.
            throw new DBProxyException("No DB connection available.");
        }

        try{
            Statement st = this.dbConn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String val = "";
            while(rs.next()){
                val = rs.getObject("username", String.class);
            }
            return "";
        }
        catch(SQLException ex){
            throw new DBProxyException("SQL fetching failed.");
        }
    }

    @Override
    public void executeUpdateQuery(String query) throws DBProxyException {
        // TEST
        if(!this.isTransaction){
            // Throw error if there is current transaction available.
            throw new DBProxyException("No transaction is currently initiated.");
        }

        if(this.isClosed()){
            // Throw error if there is no db connection instance.
            throw new DBProxyException("No DB connection available.");
        }

        try{
            this.dbConn.nativeSQL(query);
        }
        catch(SQLException ex){
            throw new DBProxyException("SQL update failed.");
        }
    }

    @Override
    public void commit() throws DBProxyException {
        // TEST
        try{
            if(!this.isTransaction){
                // Throw error if there is current transaction available.
                throw new DBProxyException("No transaction is currently initiated.");
            }

            if(this.isClosed()){
                // Throw error if there is no db connection instance.
                throw new DBProxyException("No DB connection available.");
            }

            // Reset the transaction state.
            this.isTransaction = false;
            // Commit transaction.
            this.dbConn.commit();
        }
        catch(SQLException ex){
            // Throw error if committing process fails.
            throw new DBProxyException("Could not commit transaction");
        }
    }

    @Override
    public void cancel() throws DBProxyException {
        // TEST
        try {
            if(this.isClosed()){
                // Throw error if there is no db connection instance.
                throw new DBProxyException("No DB connection available.");
            }

            // Reset transactions state.
            this.isTransaction = false;
            // Cancel transaction.
            this.dbConn.rollback();
        }
        catch(SQLException ex){
            // Throw error if rollback process fails.
            throw new DBProxyException("Could not cancel transaction.");
        }
    }

    @Override
    public void close() throws DBProxyException {
        // TEST
        try {
            if(!this.isClosed()) {
                // Close db connection.
                this.dbConn.close();
            }
        }
        catch(SQLException ex){
            // Throw error if closing connection fails.
            throw new DBProxyException("Could not close connection.");
        }
    }
}

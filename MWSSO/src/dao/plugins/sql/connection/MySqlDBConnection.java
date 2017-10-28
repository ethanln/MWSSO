package dao.plugins.sql.connection;

import dao.iconnection.IDBConnection;
import exception.DBConnectionException;
import shared.dataset.*;
import shared.util.ConfigurationManager;

import java.sql.*;

/**
 * Created by ethan on 6/22/17.
 */
public class MySqlDBConnection implements IDBConnection {

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
     * @throws DBConnectionException
     */
    public MySqlDBConnection() throws DBConnectionException {
        this.isTransaction = false;
        try {
            this.init();
        }
        catch(DBConnectionException ex){
            throw ex;
        }
    }

    /**
     * Initiates db iconnection parameters.
     * @throws DBConnectionException
     */
    private void init() throws DBConnectionException {
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
            // Build iconnection string.
            this.connectionString = "jdbc:" + service + "://" + domain + ":" + port + "/" +  database + "?user=" + username + "&password=" + password;
        }
        catch(DBConnectionException ex){
            throw ex;
        }
    }

    /**
     * Registers the used db driver.
     * @param driver
     * @throws DBConnectionException
     */
    private void registerDriver(String driver) throws DBConnectionException
    {
        try {
            // Register DB driver.
            Class.forName(driver).newInstance();
        }
        catch(ClassNotFoundException ex){
            // throw error if class is not found during reflection process.
            throw new DBConnectionException("Class Not Found - Could not register driver.");
        }
        catch(IllegalAccessException ex){
            // throw error if there is illegal access during reflection process.
            throw new DBConnectionException("Illegal Access - Could not register driver.");
        }
        catch(InstantiationException ex){
            // Throw error if there is an instantiation problem during the reflection process.
            throw new DBConnectionException("Instantiation - Could not register driver.");
        }
    }

    /**
     * Checks to see if db iconnection is closed.
     * @return
     */
    private boolean isClosed(){
        try {
            return this.dbConn == null || this.dbConn.isClosed();
        }
        catch(SQLException ex){
            return true;
        }
    }

    @Override
    public void open() throws DBConnectionException {
        if(!this.isClosed()){
            throw new DBConnectionException("Connection is already opened.");
        }
        try {
            // Create db iconnection instance.
            this.dbConn = DriverManager.getConnection(this.connectionString);
        }
        catch(SQLException ex){
            // Throw db iconnection error if iconnection fails.
            String message = "SQLException: " + ex.getMessage() + "\n" +
                             "SQLState: " + ex.getSQLState() + "\n" +
                             "VendorError: " + ex.getErrorCode();
            throw new DBConnectionException(message);
        }
    }

    @Override
    public void begin() throws DBConnectionException {
        if(this.isTransaction){
            throw new DBConnectionException("There is already a transaction in progress.");
        }
        if (this.isClosed()) {
            // Throw error if there is no db iconnection instance.
            throw new DBConnectionException("No database iconnection available.");
        }

        try {
            // Begin a transaction.
            this.dbConn.setAutoCommit(false);
            // Initiate transaction state.
            this.isTransaction = true;
        }
        catch(SQLException ex){
            // Throw error if there is no db iconnection instance.
            throw new DBConnectionException("No database iconnection available.");
        }
    }

    @Override
    public DataSet executeQuery(String queryStatement) throws DBConnectionException {
        if(this.isClosed()){
            // Throw error if there is no db iconnection instance.
            throw new DBConnectionException("No DB iconnection available.");
        }
        DataSet ds = new DataSet();
        Statement st = null;
        ResultSet rs = null;

        // split the queries into separate iterations.
        String[] queries = queryStatement.split(";(?=(?:(?:[^\"']*\"[^\"']*\")|(?:[^'\"]*'[^'\"]*'))*[^\"']*$)");

        // Execute each query and store them in a data table.
        for(String query : queries) {
            try {
                // Create a statement instance.
                st = this.dbConn.createStatement();
                // Execute query statement.
                rs = st.executeQuery(query);
                // Grab row meta data.
                ResultSetMetaData rowMeta = rs.getMetaData();

                // Get number of columns.
                int colCount = rowMeta.getColumnCount();
                // Instantiate the table set.
                DataTable table = new DataTable();

                // Load table.
                while (rs.next()) {
                    DataRow row = new DataRow();
                    for (int col = 1; col <= colCount; col++) {
                        // Fetch property of column.
                        String propertyName = rowMeta.getColumnLabel(col);
                        // Fetch value of column in row.
                        String value = rs.getString(col);
                        row.add(propertyName, value);
                    }
                    table.rows.add(row);
                }
                ds.tables.add(table);
            }
            catch (SQLException ex) {
                throw new DBConnectionException("SQL fetching failed.");
            }
            finally {
                try {
                    if(st != null) {
                        // Close statement instance.
                        st.close();
                    }
                    if(rs != null) {
                        // Close result set.
                        rs.close();
                    }
                }
                catch (SQLException ex) {
                    throw new DBConnectionException("Could not close statement.");
                }
            }
        }
        return ds;
    }

    @Override
    public int executeUpdate(String update) throws DBConnectionException{
        if(!this.isTransaction){
            // Throw error if there is current transaction available.
            throw new DBConnectionException("No transaction is currently initiated.");
        }

        if(this.isClosed()){
            // Throw error if there is no db iconnection instance.
            throw new DBConnectionException("No DB iconnection available.");
        }
        Statement st = null;
        try{
            // Create a statement instance.
            st = this.dbConn.createStatement();
            // Execute data manipulation process.
            return st.executeUpdate(update);
        }
        catch(SQLException ex){
            throw new DBConnectionException("SQL update failed.");
        }
        finally{
            try {
                if (st != null) {
                    st.close();
                }
            }
            catch(SQLException ex){
                throw new DBConnectionException("Could not close statement.");
            }
        }
    }

    @Override
    public void commit() throws DBConnectionException {
        if(!this.isTransaction){
            // Throw error if there is current transaction available.
            throw new DBConnectionException("No transaction is currently initiated.");
        }

        if(this.isClosed()){
            // Throw error if there is no db iconnection instance.
            throw new DBConnectionException("No DB iconnection available.");
        }

        try{
            // Commit transaction.
            this.dbConn.commit();
            // set auto commit to true;
            this.dbConn.setAutoCommit(true);
        }
        catch(SQLException ex){
            // Throw error if committing process fails.
            throw new DBConnectionException("Could not commit transaction");
        }
        finally{
            // Reset transaction state.
            this.isTransaction = false;
        }
    }

    @Override
    public void cancel() throws DBConnectionException {
        if(!this.isTransaction){
            // Throw error if there is current transaction available.
            throw new DBConnectionException("No transaction is currently initiated.");
        }

        if(this.isClosed()){
            // Throw error if there is no db iconnection instance.
            throw new DBConnectionException("No DB iconnection available.");
        }

        try {
            // Cancel transaction.
            this.dbConn.rollback();
            // set auto commit to true;
            this.dbConn.setAutoCommit(true);
        }
        catch(SQLException ex){
            // Throw error if rollback process fails.
            throw new DBConnectionException("Could not cancel transaction.");
        }
        finally{
            // Reset transaction state.
            this.isTransaction = false;
        }
    }

    @Override
    public void close() throws DBConnectionException {
        try {
            if(!this.isClosed()) {
                // Close db iconnection.
                this.dbConn.close();
            }
        }
        catch(SQLException ex){
            // Throw error if closing iconnection fails.
            throw new DBConnectionException("Could not close iconnection.");
        }
        finally{
            // Deallocate iconnection instance.
            this.dbConn = null;
            // Make sure the transaction state is always false.
            this.isTransaction = false;
        }
    }
}

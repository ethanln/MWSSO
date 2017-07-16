package test.db_test;

import dao.connection.MySqlDBConnection;
import exception.DBConnectionException;
import org.junit.Assert;
import org.junit.Test;
import shared.dataset.DataSet;
import shared.util.ConfigurationManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by ethan on 6/23/17.
 */
public class MySqlDBConnectionTest {
    @Test
    public void testWithClosedConnection(){
        ConfigurationManager.setConfigurationType(ConfigurationManager.ConfigurationType.TESTING);
        String message = "";
        try {
            MySqlDBConnection d = new MySqlDBConnection();

            // BEGIN TRANSACTION ON A CLOSED CONNECTION.
            try {
                d.begin();
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("No database connection available.", message);
                message = "";
                d.close();
            }

            // EXECUTE QUERY ON A CLOSED CONNECTION.
            try {
                DataSet result = d.executeQuery("SELECT * FROM SSOUser WHERE username = 'test'");
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("No DB connection available.", message);
                message = "";
                d.close();
            }

            // OPEN AND THEN COMMIT TRANSACTION.
            try {
                d.open();
                d.commit();
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("No transaction is currently initiated.", message);
                message = "";
                d.close();
            }

            //OPEN AND THEN CANCEL TRANSACTION.
            try {
                d.open();
                d.cancel();
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("No transaction is currently initiated.", message);
                message = "";
                d.close();
            }

            // OPEN CONNECTION, AND THEN TRY TO CREATE TWO TRANSACTIONS SIMULTANEOUSLY.
            try {
                d.open();
                d.begin();
                d.begin();
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("There is already a transaction in progress.", message);
                message = "";
                d.close();
            }

            // TRY OPENING CONNECTION TWICE.
            try {
                d.open();
                d.open();
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("Connection is already opened.", message);
                message = "";
                d.close();
            }

        }
        catch(DBConnectionException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testTransactions(){
        ConfigurationManager.setConfigurationType(ConfigurationManager.ConfigurationType.TESTING);
        String message = "";
        DataSet ds = null;
        try {
            MySqlDBConnection conn = new MySqlDBConnection();
            String sqlUpdate = "INSERT INTO SSOUser (username, password, email, is_active, date_created)"
                                + " VALUES('al', 'albertson', 'al@al.com', true, '" + new Date(Calendar.getInstance().getTimeInMillis()) + "')";

            // TRY UPDATE WITHOUT TRANSACTION
            try {
                conn.open();
                conn.executeUpdate(sqlUpdate);
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("No transaction is currently initiated.", message);
                message = "";
                conn.close();
            }

            // TRY UPDATE WITH TRANSACTION
            conn.open();
            conn.begin();
            int rowsAffected = conn.executeUpdate(sqlUpdate);
            Assert.assertEquals(1, rowsAffected);

            // TRY OPENING TRANSACTION AGAIN.
            try{
                conn.begin();
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("There is already a transaction in progress.", message);
                message = "";
            }

            // TEST TO SEE IF NEW RECORD PERSISTED.
            String sqlQuery = "SELECT * FROM SSOUser WHERE username = 'al'";
            conn.commit();
            ds = conn.executeQuery(sqlQuery);

            Assert.assertEquals("al", ds.tables.get(0).rows.get(0).get("username"));
            Assert.assertEquals("albertson", ds.tables.get(0).rows.get(0).get("password"));
            Assert.assertEquals("al@al.com", ds.tables.get(0).rows.get(0).get("email"));
            Assert.assertEquals("1", ds.tables.get(0).rows.get(0).get("is_active"));

            ds = null;

            // TRY DELETING USER RECORD WITHOUT TRANSACTION.
            String sqlDelete = "DELETE FROM SSOUser WHERE username = 'al'";
            try {
                conn.executeUpdate(sqlDelete);
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("No transaction is currently initiated.", message);
                message = "";
            }

            // DELETE USER RECORD
            conn.begin();
            rowsAffected = conn.executeUpdate(sqlDelete);
            Assert.assertEquals(1, rowsAffected);
            conn.commit();

            // TRY DELETING NON-EXISTENT RECORD
            conn.begin();
            rowsAffected = conn.executeUpdate(sqlDelete);
            Assert.assertEquals(0, rowsAffected);
            conn.commit();

            // TRY QUERYING NON-EXISTENT RECORD
            ds = conn.executeQuery(sqlQuery);
            Assert.assertEquals(0, ds.tables.get(0).rows.size());

            // INSERT USER RECORD WITH A CANCEL OPERATION.
            conn.begin();
            rowsAffected = conn.executeUpdate(sqlUpdate);
            Assert.assertEquals(1, rowsAffected);
            conn.cancel();
            ds = conn.executeQuery(sqlQuery);
            Assert.assertEquals(0, ds.tables.get(0).rows.size());

            // TEST QUERYING MULTIPLE TABLES
            String multQuery = "SELECT * FROM SSOUser WHERE username = 'al'; SELECT * FROM SSOUserPermission;";
            ds = conn.executeQuery(multQuery);
            Assert.assertEquals(0, ds.tables.get(0).rows.size());
            Assert.assertEquals("1", ds.tables.get(1).rows.get(0).get("id"));
            Assert.assertEquals("1", ds.tables.get(1).rows.get(0).get("sso_user_id"));
            Assert.assertEquals("1", ds.tables.get(1).rows.get(0).get("sso_group_permission_id"));

            // TEST APPLYING MANIPULATION SCRIPT TO FETCH OPERATION
            try {
                conn.executeQuery(sqlUpdate);
            }
            catch(DBConnectionException ex){
                message = ex.getMessage();
            }
            finally{
                Assert.assertEquals("SQL fetching failed.", message);
            }

            conn.close();
        }
        catch(DBConnectionException ex){
            Assert.assertEquals(0, 1);
        }
    }
}

package test.util_test;

import dao.proxy.MySqlDBProxy;
import exception.DBProxyException;
import org.junit.Assert;
import org.junit.Test;
import shared.util.ConfigurationManager;

import java.sql.ResultSet;

/**
 * Created by ethan on 6/23/17.
 */
public class MySqlDBProxyTest {
    @Test
    public void testWithClosedConnection(){
        ConfigurationManager.setConfigurationType(ConfigurationManager.ConfigurationType.TESTING);
        try {
            MySqlDBProxy d = new MySqlDBProxy();

            // BEGIN TRANSACTION ON A CLOSED CONNECTION.
            try {
                d.begin();
            }
            catch(DBProxyException ex){
                Assert.assertEquals("No database connection available.", ex.getMessage());
            }
            finally{d.close();}

            // EXECUTE QUERY ON A CLOSED CONNECTION.
            try {
                ResultSet result = d.executeQuery("SELECT * FROM SSOUser WHERE username = 'test'");
            }
            catch(DBProxyException ex){
                Assert.assertEquals("No transaction is currently initiated.", ex.getMessage());
            }
            finally{d.close();}

            // OPEN AND THEN COMMIT TRANSACTION.
            try {
                d.open();
                d.commit();
            }
            catch(DBProxyException ex){
                Assert.assertEquals("No transaction is currently initiated.", ex.getMessage());
            }
            finally{d.close();}

            //OPEN AND THEN CANCEL TRANSACTION.
            try {
                d.open();
                d.cancel();
            }
            catch(DBProxyException ex){
                Assert.assertEquals("No transaction is currently initiated.", ex.getMessage());
            }
            finally{d.close();}

            // OPEN CONNECTION, CREATE TRANSACTION, CLOSE CONNECTION, AND THEN EXECUTE QUERY.
            try {
                d.open();
                d.begin();
                d.close();
                d.executeQuery("SELECT * FROM SSOUser WHERE username = 'test'");
            }
            catch(DBProxyException ex){
                Assert.assertEquals("No transaction is currently initiated.", ex.getMessage());
            }
            finally{d.close();}

            // OPEN CONNECTION, AND THEN TRY TO CREATE TWO TRANSACTIONS SIMULTANEOUSLY.
            try {
                d.open();
                d.begin();
                d.begin();
            }
            catch(DBProxyException ex){
                Assert.assertEquals("There is already a transaction in progress.", ex.getMessage());
            }
            finally{d.close();}

            // TRY OPENING CONNECTION TWICE.
            try {
                d.open();
                d.open();
            }
            catch(DBProxyException ex){
                Assert.assertEquals("Connection is already opened.", ex.getMessage());
            }
            finally{d.close();}

        }
        catch(DBProxyException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testQueries(){

    }
}

package test.util_test;

import dao.proxy.MySqlDBProxy;
import exception.DBProxyException;
import org.junit.Test;

import java.sql.ResultSet;

/**
 * Created by ethan on 6/23/17.
 */
public class MySqlDBProxyTest {
    @Test
    public void testConnection(){
        try {
            MySqlDBProxy d = new MySqlDBProxy();
            d.open();
            ResultSet result = d.executeQuery("SELECT * FROM SSOUser");

        }
        catch(DBProxyException ex){
            System.out.println(ex.getMessage());
        }
    }
}

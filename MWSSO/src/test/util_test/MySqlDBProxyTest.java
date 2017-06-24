package test.util_test;

import dao.proxy.MySqlDBProxy;
import exception.DBProxyException;
import org.junit.Test;
/**
 * Created by ethan on 6/23/17.
 */
public class MySqlDBProxyTest {
    @Test
    public void testConnection(){
        String result = "";
        try {
            MySqlDBProxy d = new MySqlDBProxy();
            d.open();
            result = d.executeFetchQuery("SELECT * FROM SSOUser");
        }
        catch(DBProxyException ex){
            System.out.println(ex.getMessage());
        }
    }
}

package test.util_test;

import org.junit.*;
import org.junit.Assert;
import shared.util.ConfigurationManager;

/**
 * Created by ethan on 6/18/17.
 */
public class ConfigManagerTest {
    @Test
    public void testConfigQueries(){

        ConfigurationManager.setConfigurationType(ConfigurationManager.ConfigurationType.TESTING);

        String result = (String)ConfigurationManager.fetchDbConfiguration("service");
        Assert.assertEquals(result, "mysql");

        result = (String)ConfigurationManager.fetchDbConfiguration("username");
        Assert.assertEquals(result, "root");

        result = (String)ConfigurationManager.fetchDbConfiguration("database");
        Assert.assertEquals(result, "testdb");

        result = (String)ConfigurationManager.fetchDbConfiguration("domain");
        Assert.assertEquals(result, "localhost");

        result = (String)ConfigurationManager.fetchDbConfiguration("driver");
        Assert.assertEquals(result, "com.mysql.jdbc.Driver");

        result = (String)ConfigurationManager.fetchDbConfiguration("port");
        Assert.assertEquals(result, "3306");

        result = (String)ConfigurationManager.fetchDbConfiguration("port.whoKnows");
        Assert.assertEquals(result, null);
    }
}

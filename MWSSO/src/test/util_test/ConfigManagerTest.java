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
        Assert.assertEquals(result, "test");

        result = (String)ConfigurationManager.fetchDbConfiguration("username");
        Assert.assertEquals(result, "test");

        result = (String)ConfigurationManager.fetchDbConfiguration("password");
        Assert.assertEquals(result, "tester");

        result = (String)ConfigurationManager.fetchDbConfiguration("database");
        Assert.assertEquals(result, "testdb");

        result = (String)ConfigurationManager.fetchDbConfiguration("domain");
        Assert.assertEquals(result, "test_domain");

        result = (String)ConfigurationManager.fetchDbConfiguration("driver");
        Assert.assertEquals(result, "test.Driver");

        result = (String)ConfigurationManager.fetchDbConfiguration("port");
        Assert.assertEquals(result, "1111");

        result = (String)ConfigurationManager.fetchDbConfiguration("port.whoKnows");
        Assert.assertEquals(result, null);
    }
}

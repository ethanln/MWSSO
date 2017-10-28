package test.util_test;

import com.google.gson.internal.LinkedTreeMap;
import org.junit.*;
import org.junit.Assert;
import shared.util.ConfigurationManager;

import java.util.ArrayList;

/**
 * Created by ethan on 6/18/17.
 */
public class ConfigManagerTest {
    @Test
    public void testDBConfigQueries(){

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

        result = (String)ConfigurationManager.fetchDaoDependencyConfiguration("SSOSessionTokenDao[0].property_name");
        Assert.assertEquals(result, "ssoUserDao");

        ArrayList<LinkedTreeMap> dependencies = (ArrayList<LinkedTreeMap>)ConfigurationManager.fetchDaoDependencyConfiguration("SSOSessionTokenDao");
        result = dependencies.get(0).get("class_name").toString();
        Assert.assertEquals(result, "SSOUserDao");

        result = dependencies.get(1).get("property_name").toString();
        Assert.assertEquals(result, "ssoUserPermissionDao");

        result = (String)ConfigurationManager.fetchDaoDependencyConfiguration("SSOSessionTokenDao[0].whoKnows");
        Assert.assertEquals(result, null);

        result = (String)ConfigurationManager.fetchDaoSetRegistryConfiguration("registry");
        Assert.assertEquals(result, "sql");
    }
}

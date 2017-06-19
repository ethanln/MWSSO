package test.util_test;

import org.junit.Test;
import shared.util.ConfigManager;

/**
 * Created by ethan on 6/18/17.
 */
public class ConfigManagerTest {
    @Test
    public void testInstantiation(){
        Object result = ConfigManager.getInstance().fetchDbConfiguration("clients[1].term");
        result = ConfigManager.getInstance().fetchDbConfiguration("clients[1]");
        result = ConfigManager.getInstance().fetchDbConfiguration("clients");
        result = ConfigManager.getInstance().fetchDbConfiguration("clients[0].dontKnow");
        result = ConfigManager.getInstance().fetchDbConfiguration("");
        result = ConfigManager.getInstance().fetchDbConfiguration(null);
    }
}

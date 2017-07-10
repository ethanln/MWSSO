package test;

/**
 * Created by ethan on 7/10/17.
 */
public class TestRunner {
    public static void main(String args[]){
        String[] testClassesServer = new String[] {
            "test.util_test.ConfigManagerTest",
            "test.db_test.MySqlDBConnectionTest"
        };
        org.junit.runner.JUnitCore.main(testClassesServer);
    }
}

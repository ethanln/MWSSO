package test.dao_test;

import dao.DaoFactory;
import exception.DaoFactoryException;
import org.junit.Test;
import shared.util.ConfigurationManager;

/**
 * Created by ethan on 10/27/17.
 */
public class DaoFactoryTest {

    @Test
    public void test(){
        ConfigurationManager.setConfigurationType(ConfigurationManager.ConfigurationType.TESTING);
        try{
            DaoFactory factory = new DaoFactory("sql");
        }
        catch(DaoFactoryException ex){

        }

    }
}

package test.util_test;

import dao.Dao;
import exception.DaoException;
import org.junit.Test;
/**
 * Created by ethan on 6/23/17.
 */
public class DaoTest {
    @Test
    public void testConnection(){
        try {
            Dao d = new Dao();
            d.open();
        }
        catch(DaoException ex){
            System.out.println(ex.getMessage());
        }
    }
}

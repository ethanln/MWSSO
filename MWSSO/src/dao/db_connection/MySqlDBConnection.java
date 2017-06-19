package dao.db_connection;


import exception.DBConnectionException;

/**
 * Created by ethan on 6/18/17.
 */
public class MySqlDBConnection implements IDBConnection {

    private String connectionString;

    @Override
    public void init(){

    }

    @Override
    public void open(){

    }

    @Override
    public void begin(){

    }

    @Override
    public String executeQuery(String query) throws DBConnectionException{
        throw new DBConnectionException("Not yet implemented.");
    }

    @Override
    public void commit() {

    }

    @Override
    public void cancel(){

    }

    @Override
    public void close(){

    }
}

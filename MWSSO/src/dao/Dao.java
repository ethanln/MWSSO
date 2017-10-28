package dao;

import dao.iconnection.IDBConnection;
import dao.plugins.sql.connection.MySqlDBConnection;
import exception.DBConnectionException;
import exception.DaoException;
import shared.util.ConfigurationManager;

/**
 * Created by ethan on 8/20/17.
 */
public abstract class Dao {

    /**
     * DB iconnection instance.
     */
    protected IDBConnection dbConnection;

    /**
     * Constructor.
     * @throws DaoException
     */
    public Dao() throws DaoException {
        // TEST
        String dbService = ConfigurationManager.fetchDbConfiguration("service").toString();
        this.instantiateDBConnection(dbService);
    }

    /**
     * Instantiates the DB iconnection reference.
     * @param dbService
     * @throws DaoException
     */
    private void instantiateDBConnection(String dbService) throws DaoException{
        try {
            switch(dbService) {
                case "mysql":
                    this.dbConnection = new MySqlDBConnection();
            }
        }
        catch(DBConnectionException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

}

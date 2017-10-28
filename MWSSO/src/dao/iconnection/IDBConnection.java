package dao.iconnection;

import exception.DBConnectionException;
import shared.dataset.DataSet;

/**
 * Created by ethan on 6/24/17.
 */
public interface IDBConnection {
    /**
     * Opens a iconnection to a db server.
     * @throws DBConnectionException
     */
    void open() throws DBConnectionException;

    /**
     * Begins transaction to db server.
     * @throws DBConnectionException
     */
    void begin() throws DBConnectionException;

    /**
     * Executes a fetch query and returns raw string data.
     * @param queryStatement
     * @return
     * @throws DBConnectionException
     */
    DataSet executeQuery(String queryStatement) throws DBConnectionException;

    /**
     * Executes an sql update the database.
     * @param update
     * @return
     * @throws DBConnectionException
     */
    int executeUpdate(String update) throws DBConnectionException;

    /**
     * Commits transaction to db server.
     * @throws DBConnectionException
     */
    void commit() throws DBConnectionException;

    /**
     * Cancels transaction to db server.
     * @throws DBConnectionException
     */
    void cancel() throws DBConnectionException;

    /**
     * Closes iconnection to db server.
     * @throws DBConnectionException
     */
    void close() throws DBConnectionException;
}

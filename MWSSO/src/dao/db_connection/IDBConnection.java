package dao.db_connection;

import exception.DBConnectionException;

/**
 * Created by ethan on 6/18/17.
 */
public interface IDBConnection {
    /**
     * Initiates db connection parameters.
     * @throws DBConnectionException
     */
    void init() throws DBConnectionException;

    /**
     * Opens a connection to a db server.
     * @throws DBConnectionException
     */
    void open() throws DBConnectionException;

    /**
     * Begins transaction to db server.
     * @throws DBConnectionException
     */
    void begin() throws DBConnectionException;

    /**
     * Executes a query and returns raw string data.
     * @param query
     * @return
     * @throws DBConnectionException
     */
    String executeQuery(String query) throws DBConnectionException;

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
     * Closes connection to db server.
     * @throws DBConnectionException
     */
    void close() throws DBConnectionException;
}

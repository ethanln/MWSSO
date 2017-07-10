package dao.connection;

import exception.DBConnectionException;

import java.sql.ResultSet;

/**
 * Created by ethan on 6/24/17.
 */
public interface IDBConnection {
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
     * Executes a fetch query and returns raw string data.
     * @param query
     * @return
     * @throws DBConnectionException
     */
    ResultSet executeQuery(String query) throws DBConnectionException;

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
     * Closes connection to db server.
     * @throws DBConnectionException
     */
    void close() throws DBConnectionException;
}

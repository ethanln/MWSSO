package dao.proxy;

import exception.DBProxyException;

import java.sql.ResultSet;

/**
 * Created by ethan on 6/24/17.
 */
public interface IDBProxy {
    /**
     * Opens a connection to a db server.
     * @throws DBProxyException
     */
    void open() throws DBProxyException;

    /**
     * Begins transaction to db server.
     * @throws DBProxyException
     */
    void begin() throws DBProxyException;

    /**
     * Executes a fetch query and returns raw string data.
     * @param query
     * @return
     * @throws DBProxyException
     */
    ResultSet executeQuery(String query) throws DBProxyException;

    /**
     * Commits transaction to db server.
     * @throws DBProxyException
     */
    void commit() throws DBProxyException;

    /**
     * Cancels transaction to db server.
     * @throws DBProxyException
     */
    void cancel() throws DBProxyException;

    /**
     * Closes connection to db server.
     * @throws DBProxyException
     */
    void close() throws DBProxyException;
}

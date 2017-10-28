package dao.plugins.sql.factory;

import dao.ifactory.IDaoFactory;
import dao.idao.*;
import dao.plugins.sql.dao.*;
import exception.DaoException;
import exception.DaoFactoryException;

/**
 * Created by ethan on 10/27/17.
 */
public class SqlDaoFactory implements IDaoFactory {

    @Override
    public ISSOGroupPermissionDao createSSOGroupPermissionDao() throws DaoFactoryException{
        try {
            return new SSOGroupPermissionSqlDao();
        }
        catch(DaoException ex) {
            throw new DaoFactoryException("Could create sso group permission sql dao");
        }
    }

    @Override
    public ISSOSessionTokenDao createSSOSessionTokenDao() throws DaoFactoryException{
        try {
            return new SSOSessionTokenSqlDao();
        }
        catch(DaoException ex) {
            throw new DaoFactoryException("Could create sso session token sql dao");
        }
    }

    @Override
    public ISSOUserDao createSSOUserDao() throws DaoFactoryException{
        try {
            return new SSOUserSqlDao();
        }
        catch(DaoException ex) {
            throw new DaoFactoryException("Could create sso user sql dao");
        }
    }

    @Override
    public ISSOUserPermissionDao createSSOUserPermissionDao() throws DaoFactoryException{
        try {
            return new SSOUserPermissionSqlDao();
        }
        catch(DaoException ex) {
            throw new DaoFactoryException("Could create sso user permission sql dao");
        }
    }

    @Override
    public ISSOUserRegistrationRequestDao createSSOUserRegistrationRequestDao() throws DaoFactoryException{
        try {
            return new SSOUserRegistrationRequestSqlDao();
        }
        catch(DaoException ex) {
            throw new DaoFactoryException("Could create sso user registration request sql dao");
        }
    }
}

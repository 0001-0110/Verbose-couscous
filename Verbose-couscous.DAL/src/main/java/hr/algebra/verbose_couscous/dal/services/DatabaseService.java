/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.verbose_couscous.dal.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author remi
 */
public class DatabaseService {

    // TODO Find a place to put all this

    @FunctionalInterface
    public interface CheckedFunction<TArg, TResult, TException extends Exception> {
        TResult apply(TArg argument) throws TException;
    }

    public interface SQLCheckedFunction<TArg, TResult> extends CheckedFunction<TArg, TResult, SQLException> { }

    public interface ResultHandler<TResult> extends SQLCheckedFunction<ResultSet, TResult> { }

    @FunctionalInterface
    public interface CheckedConsumer<TArg, TException extends Exception> {
        void accept(TArg argument) throws TException;
    }

    public interface SQLCheckedConsumer<TArg> extends CheckedConsumer<TArg, SQLException> { }

    public interface StatementInitializer extends SQLCheckedConsumer<CallableStatement> { }

    public String serverName;
    public String databaseName;
    public String username;
    public String password;

    private static final Properties properties = new Properties();
    private DataSource dataSource;

    public DatabaseService() {

        // TODO get the path
        try (InputStream inputStream = DatabaseService.class.getResourceAsStream("")) {
            properties.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Prepare the database connection
        dataSource = getDataSource();
    }

    private DataSource getDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(properties.getProperty(serverName));
        dataSource.setDatabaseName(properties.getProperty(databaseName));
        dataSource.setUser(properties.getProperty(username));
        dataSource.setPassword(properties.getProperty(password));
        return dataSource;
    }

    public void sendQuery(String procedure) {
        sendQuery(procedure, statement -> { });
    }

    public void sendQuery(String procedure, StatementInitializer statementInitializer) {
        sendQuery(procedure, statementInitializer, resultSet -> Optional.empty());
    }

    public <TResult> Optional<TResult> sendQuery(String procedure, StatementInitializer statementInitializer, ResultHandler<TResult> resultHandler) {
        try (Connection connection = dataSource.getConnection();
                CallableStatement statement = connection.prepareCall(procedure)) {

            statementInitializer.accept(statement);
            return Optional.of(resultHandler.apply(statement.executeQuery()));
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}

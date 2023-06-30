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

    public interface SQLCheckedFunction<TArg, TResult> extends CheckedFunction<TArg, TResult, SQLException> {
    }

    public interface ResultHandler<TResult> extends SQLCheckedFunction<ResultSet, TResult> {
    }

    @FunctionalInterface
    public interface CheckedConsumer<TArg, TException extends Exception> {
        void accept(TArg argument) throws TException;
    }

    public interface SQLCheckedConsumer<TArg> extends CheckedConsumer<TArg, SQLException> {
    }

    public interface StatementInitializer extends SQLCheckedConsumer<CallableStatement> {
    }

    public final String PROPERTIESPATH = "/config/database.properties";
    public final String SERVERNAME = "SERVER";
    public final String DATABASENAME = "DATABASE";
    public final String USERNAME = "USERNAME";
    public final String PASSWORD = "PASSWORD";

    private static final Properties properties = new Properties();
    private DataSource dataSource;

    public DatabaseService() {

        // Load the properties from the config file
        try (InputStream inputStream = DatabaseService.class.getResourceAsStream(PROPERTIESPATH)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Prepare the database connection
        dataSource = getDataSource();
    }

    private DataSource getDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(properties.getProperty(SERVERNAME));
        dataSource.setDatabaseName(properties.getProperty(DATABASENAME));
        dataSource.setUser(properties.getProperty(USERNAME));
        dataSource.setPassword(properties.getProperty(PASSWORD));
        return dataSource;
    }

    public void sendQuery(String procedure) {
        sendQuery(procedure, statement -> {
        });
    }

    public void sendQuery(String procedure, StatementInitializer statementInitializer) {
        try (Connection connection = dataSource.getConnection();
                CallableStatement statement = connection.prepareCall(procedure)) {

            statementInitializer.accept(statement);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public <TResult> Optional<TResult> sendQuery(String procedure, StatementInitializer statementInitializer,
            ResultHandler<TResult> resultHandler) {
        try (Connection connection = dataSource.getConnection();
                CallableStatement statement = connection.prepareCall(procedure)) {

            statementInitializer.accept(statement);
            return Optional.of(resultHandler.apply(statement.executeQuery()));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}

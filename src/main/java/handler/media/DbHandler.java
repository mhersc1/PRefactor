package handler.media;

import bean.Trace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbHandler extends MediaHandler {
    private static DbHandler instance;
    private Connection connection;

    private DbHandler() {
        try {
            Properties connectionProps = loadProperties();
            connection = DriverManager.getConnection("jdbc:" + connectionProps.getProperty("dbms") + "://" + connectionProps.getProperty("serverName")
                    + ":" + connectionProps.getProperty("portNumber") + "/" + connectionProps.getProperty("database") + "?useSSL=false", connectionProps.getProperty("user"), connectionProps.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("It happened an error at creating database connection");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbHandler getInstance() {
        try {
            if (instance == null) {
                instance = new DbHandler();
            } else if (instance.getConnection().isClosed()) {
                instance = new DbHandler();
            }
            return instance;
        } catch (SQLException e) {
            throw new RuntimeException("It happened an error at getting database connection");
        }
    }

    @Override
    public void printTrace(Trace trace) {
        try {
            if (!trace.isValid())
                return;

            Statement stmt = this.connection.createStatement();
            stmt.execute("create table if not exists log_values (messageTxt VARCHAR(500), type INT)");
            stmt.executeUpdate("insert into log_values values ('" + trace.getMessageText() + "', '" + trace.getType() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("It happened an error at attempting inserting log values");
        }
    }
}

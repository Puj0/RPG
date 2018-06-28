package database;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionRPG {

    private Connection connection = null;
    private Statement statement;
    private static ConnectionRPG instance;
    private String URL;
    private String USERNAME;
    private String PASSWORD;

    private ConnectionRPG() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("database.properties"));
        URL = properties.getProperty("url");
        USERNAME = properties.getProperty("user");
        PASSWORD = properties.getProperty("password");
        String DRIVER = properties.getProperty("driver");
        Class.forName(DRIVER);
    }

    public static ConnectionRPG getInstance() throws Exception {
        if (instance == null) {
            synchronized (ConnectionRPG.class) {
                if (instance == null) {
                    instance = new ConnectionRPG();
                }
            }
        }
        return instance;
    }

    public void openConnection() {
        try {
            connectTo(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.err.println("Couldn't open connection, sorry.");
        }
    }

    private void connectTo(String url, String user, String password) throws Exception {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void applyCommand(String command) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(command);
        statement.closeOnCompletion();
    }

    public ResultSet select(String query) {
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.closeOnCompletion();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return resultSet;
    }
}

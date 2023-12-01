package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectionWrapper {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://localhost/"; // 127.0.0.1

    private static final String USER = "root";

    private static final String PASSWORD = "Maria300*";

    private static final int TIMEOUT = 5;

    private Connection connection;


    public JDBConnectionWrapper(String schema){

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + schema, USER, PASSWORD);
            createTables();
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException {


    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection(){
        return connection;
    }

}
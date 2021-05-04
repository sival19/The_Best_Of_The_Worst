package persistancy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
    //set connection parameters
    private static DatabaseConnector instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "TV2";
    private String username = "postgres";
    private String password = "dfasofdasokf";
    private Connection connection = null;

    private DatabaseConnector(){
        initializePostgresqlDatabase();

    }
    public static DatabaseConnector getInstance(){
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }


    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }
}


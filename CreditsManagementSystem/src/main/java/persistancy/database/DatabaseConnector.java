package persistancy.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseConnector {
    //set connection parameters
    private static DatabaseConnector instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "TV2";
    private String username = "postgres";
    private String password = "haleluja";
    private Connection connection = null;

    private DatabaseConnector() {
        initializePostgresqlDatabase();

    }

    public static DatabaseConnector getInstance() {
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
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" +
                    port + "/" + databaseName, username, password);

//            //excecute migration SQL on app start
//            ScriptRunner sr = new ScriptRunner(connection); //imported class
//            try {
//                Reader reader = new BufferedReader(new FileReader(DatabaseConnector.class.
//                        getResource("migration.SQL").getPath()));
//                sr.runScript(reader);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }
}


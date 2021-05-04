package domain.objectMapper;

import domain.logIn.Bruger;
import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;
import persistancy.database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrugerMapper extends AbstractMapper {
    private DatabaseConnector databaseConnector;

    public BrugerMapper(){
        databaseConnector = DatabaseConnector.getInstance();
    }



    @Override
    public Object getObject(int oid) {

        PreparedStatement stmt = null;
        try {
            stmt = databaseConnector.getConnection().prepareStatement("SELECT * FROM bruger");
            stmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;


    }


    @Override
    public void putObject(Object object) {
        Bruger bruger = (Bruger) object;
        try {

            PreparedStatement stmt = databaseConnector.getConnection().prepareStatement("INSERT INTO bruger(brugernavn, email, adgangskode, rettighed) VALUES (?,?,?,?)");
            stmt.setString(1, bruger.getBrugernavn());
            stmt.setString(2, bruger.getEmail());
            stmt.setString(3, bruger.getAdgangskode());
            stmt.setString(4,bruger.getEmail());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}

package domain.objectMapper;

import domain.logIn.Bruger;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrugerMapper extends AbstractMapper {
    private DatabaseConnector databaseConnector;

    public BrugerMapper() {
        databaseConnector = DatabaseConnector.getInstance();
    }

    @Override

    public List<Object> getAllObjects() {
        List<Object> brugerList = new ArrayList<>();
        Bruger bruger = new Bruger();

        //lazy load without loading the relation 1-* program "pogram that bruger made"
        try {
            PreparedStatement stmt = databaseConnector.getConnection().
                    prepareStatement("SELECT * FROM bruger");

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {

                bruger.setBrugerID(resultSet.getInt("id"));
                bruger.setBrugernavn(resultSet.getString("brugernavn"));
                bruger.setEmail(resultSet.getString("email"));
                bruger.setAdgangskode(resultSet.getString("adgangskode"));
                bruger.setRettighed(resultSet.getString("rettighed"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return brugerList;
    }


    @Override
    public Object getObject(Object oid) {
        Bruger bruger = new Bruger();
        List<Integer> prodIdList = new ArrayList<>();
        try {
            //eager load brugere 1-* program

            //load bruger tabel først
            PreparedStatement stmtBruger = databaseConnector.getConnection().prepareStatement("SELECT * FROM bruger WHERE brugernavn = ?");
            stmtBruger.setString(1, (String) oid);

            ResultSet resultSet = stmtBruger.executeQuery();
            if (resultSet.next()) {
                bruger.setRettighed(resultSet.getString("rettighed"));
                bruger.setBrugernavn(resultSet.getString("brugernavn"));
                bruger.setAdgangskode(resultSet.getString("adgangskode"));
                bruger.setBrugerID(resultSet.getInt("id"));
            }

            //load programID fra program tabel
            PreparedStatement stmtProdID = databaseConnector.getConnection().prepareStatement("SELECT program.id FROM bruger, program WHERE brugernavn = ? AND bruger.id = program.bruger_id");
            stmtProdID.setString(1, (String) oid);
            ResultSet resultSetProdID = stmtProdID.executeQuery();

            while (resultSetProdID.next()){
                prodIdList.add(resultSetProdID.getInt("id"));
            }

            bruger.setProduktionsIDer(prodIdList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bruger;
    }


    @Override
    public boolean putObject(Object object) {
        Bruger bruger = (Bruger) object;
        try {

            PreparedStatement stmt = databaseConnector.getConnection().prepareStatement("INSERT INTO bruger(brugernavn, email, adgangskode, rettighed) VALUES (?,?,?,?)");
            stmt.setString(1, bruger.getBrugernavn());
            stmt.setString(2, bruger.getEmail());
            stmt.setString(3, bruger.getAdgangskode());
            stmt.setString(4, bruger.getRettighed().toString());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;

}}

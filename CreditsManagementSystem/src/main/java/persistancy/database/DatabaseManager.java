package persistancy.database;

import Intefaces.*;
import domain.credits.Rolle;
import domain.logIn.Bruger;
import domain.logIn.Rettighed;
import domain.objectMapper.BrugerMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements IDataManager {

    private DatabaseConnector databaseConnector;
    private IMapper iMapper;


    public DatabaseManager() {
        databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public IBruger loadBruger(String brugerNavn) {
        return null;
    }

    @Override
    public boolean saveBruger(IBruger bruger) {
        return false;
    }

    @Override
    public List<IPerson> loadPersoner() {
        return null;
    }

    @Override
    public List<IProgram> loadProgrammer() {
        return null;
    }

    @Override
    public List<IRolle> loadRoller() {
        return null;
    }

    @Override
    public Map<String, IBruger> loadbrugere() {
        return null;
    }

    @Override
    public boolean saveCatalogObject(ICatalogObject catalogObject) {

        //prepare a statement
        PreparedStatement stmt = null;
        try {
            //statement itself
            stmt = databaseConnector.getConnection().prepareStatement("INSERT INTO rolle(rolletype) VALUES (?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(1,"producer");

            //excecute statement
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {

        //use the prepared statememnt
        Bruger bruger = new Bruger("hey", "sd", "adsa", Rettighed.ADMINISTRATOR, 1);
        BrugerMapper hola = new BrugerMapper();
        hola.putObject(bruger);
    }

    @Override
    public boolean updateCatalogObject(String key, ICatalogObject catalogObject) {
        return false;
    }

    @Override
    public boolean updateBruger(String key, IBruger iBruger) {
        return false;
    }
}
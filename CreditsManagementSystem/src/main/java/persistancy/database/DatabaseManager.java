package persistancy.database;

import Intefaces.*;
import domain.credits.Rolle;
import domain.objectMapper.RolleMapper;

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
        AbstractMapper abstractMapper = new RolleMapper(databaseConnector);

        abstractMapper.putObject(catalogObject);

        return true;
    }

    public static void main(String[] args) {

        //use the prepared statememnt
        DatabaseManager hola = new DatabaseManager();
        hola.saveCatalogObject(new Rolle("Hovedperson", 2));
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
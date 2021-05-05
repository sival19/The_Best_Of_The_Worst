package persistancy.database;

import Intefaces.*;
import domain.credits.Rolle;
import domain.logIn.Bruger;
import domain.logIn.Rettighed;
import domain.objectMapper.BrugerMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements IDataManager {

    private DatabaseConnector databaseConnector;
    private IMapper iMapper;
    private static DatabaseManager instance;


    public DatabaseManager() {

    }

    public static DatabaseManager getDataInstance(){
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    @Override
    public IBruger loadBruger(String brugerNavn) {
        BrugerMapper mapper = new BrugerMapper();

        IBruger bruger = (IBruger) mapper.getObject(brugerNavn);

        return bruger;
    }

    @Override
    public boolean saveBruger(IBruger bruger) {
        BrugerMapper mapper = new BrugerMapper();
        mapper.putObject(bruger);
        return true;
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
        BrugerMapper mapper = new BrugerMapper();
        Map<String, IBruger> brugerMap = new HashMap<>();
        for(Object object : mapper.getAllObjects()){
            IBruger bruger = (IBruger) object;
            brugerMap.put(bruger.getBrugernavn(), bruger);

        }
        return brugerMap;
    }

    @Override
    public boolean saveCatalogObject(ICatalogObject catalogObject) {

        return true;
        //use the prepared statememnt

    }

    public static void main(String[] args) {

        //use the prepared statememnt

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
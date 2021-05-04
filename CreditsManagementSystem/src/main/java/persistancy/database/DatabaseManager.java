package persistancy.database;

import Intefaces.*;
import domain.credits.*;
import domain.objectMapper.ProgramMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
        iMapper  = new ProgramMapper();
        iMapper.putObject(catalogObject);
        return false;
    }

    public static void main(String[] args) {
        Program program = new Program("Danmark", 2, new Date(), ProgramType.DOKUMENTAR, Genre.ACTION, 2.20, new ArrayList<Credit>());
        //use the prepared statememnt
        DatabaseManager hola = new DatabaseManager();
        hola.saveCatalogObject(program);
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
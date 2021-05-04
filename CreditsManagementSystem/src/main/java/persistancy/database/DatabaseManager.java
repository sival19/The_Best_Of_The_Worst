package persistancy.database;

import Intefaces.*;
import domain.credits.*;
import domain.objectMapper.ProgramMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        Program program = null;
        program = new Program("Danmark", 2, new Date(1), ProgramType.DOKUMENTAR, Genre.ACTION, 2.20, new ArrayList<Credit>());
        DatabaseManager hola = new DatabaseManager();
        hola.saveCatalogObject(program);


        //use the prepared statememnt

        ProgramMapper programMapper = new ProgramMapper();
        Program program1 = (Program) programMapper.getObject(1);
        System.out.println(program1);


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
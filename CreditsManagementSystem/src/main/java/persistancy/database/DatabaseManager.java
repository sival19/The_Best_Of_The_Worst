package persistancy.database;

import Intefaces.*;
import domain.credits.Program;
import persistancy.database.objectMapper.*;
import persistancy.IDataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements IDataManager {

    private IMapper iMapper;

    private static DatabaseManager databaseManager;

    private DatabaseManager(){};

    public static DatabaseManager getInstance() {
        if(databaseManager==null){
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }
    @Override
    public IBruger loadBruger(String brugerNavn) {
        iMapper = new BrugerMapper();

        return (IBruger) iMapper.getObject(brugerNavn);
    }

    @Override
    public List<IPerson> loadPersoner() {
        iMapper = new PersonMapper();
        List<IPerson> personliste = new ArrayList<>();
        for (Object o : iMapper.getAllObjects()) {
            IPerson iPerson = (IPerson) o;
            personliste.add(iPerson);
        }
        return personliste;
    }

    @Override
    public List<IProgram> loadProgrammer() {
        iMapper = new ProgramMapper();
        ArrayList<IProgram> arrayList = new ArrayList<>();

        for (Object object : iMapper.getAllObjects()) {
            arrayList.add((IProgram) object);
        }

        return arrayList;
    }

    @Override
    public List<IRolle> loadRoller() {
        iMapper = new RolleMapper();
        List<IRolle> rolleList = new ArrayList<>();
        for (Object object : iMapper.getAllObjects()) {
            IRolle iRolle = (IRolle) object;
            rolleList.add(iRolle);
        }
        return rolleList;
    }

    @Override
    public Map<String, IBruger> loadbrugere() {
        iMapper = new BrugerMapper();
        Map<String, IBruger> brugerMap = new HashMap<>();
        for (Object object : iMapper.getAllObjects()) {
            IBruger bruger = (IBruger) object;
            brugerMap.put(bruger.getBrugernavn(), bruger);

        }
        return brugerMap;
    }


    @Override
    public boolean saveObject(Object object) {
        iMapper = null;
        if (object instanceof IPerson) {
            IPerson iPerson = (IPerson) object;
            iMapper = new PersonMapper();
            return iMapper.putObject(iPerson);
        } else if (object instanceof IProgram) {
            IProgram iProgram = (IProgram) object;
            iMapper = new ProgramMapper();
            return iMapper.putObject(iProgram);
        } else if (object instanceof IRolle) {
            IRolle iRolle = (IRolle) object;
            iMapper = new RolleMapper();
            return iMapper.putObject(iRolle);
        } else if (object instanceof IBruger) {
            IBruger iBruger = (IBruger) object;
            iMapper = new BrugerMapper();
            return iMapper.putObject(iBruger);
        }
        return false;

    }


    @Override
    public boolean updateObject(String key, Object object) {

        if (object instanceof IProgram) {
            IProgram iProgram = (IProgram) object;
            iMapper = new ProgramMapper();
            return iMapper.updateObject(iProgram);
        }

        return false;
    }


}
package persistancy.database;

import Intefaces.*;
import domain.credits.Program;
import domain.objectMapper.BrugerMapper;
import domain.objectMapper.PersonMapper;
import domain.objectMapper.ProgramMapper;
import domain.objectMapper.RolleMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements IDataManager {

    private IMapper iMapper;


    public DatabaseManager() {

    }


    @Override
    public IBruger loadBruger(String brugerNavn) {
        iMapper = new BrugerMapper();

        return (IBruger) iMapper.getObject(brugerNavn);
    }

    @Override
    public boolean saveBruger(IBruger bruger) {
        iMapper = new BrugerMapper();
        return iMapper.putObject(bruger);
    }

    @Override
    public List<IPerson> loadPersoner() {
        iMapper = new PersonMapper();
        List<IPerson> personliste = new ArrayList<>();
        for (Object o:iMapper.getAllObjects()) {
            IPerson iPerson = (IPerson) o;
            personliste.add(iPerson);
        }
        return personliste;
    }

    @Override
    public List<IProgram> loadProgrammer() {
        iMapper = new ProgramMapper();
        ArrayList<IProgram> arrayList = new ArrayList<>();

        for(Object object: iMapper.getAllObjects()){
            arrayList.add((IProgram) object);
        }

        return arrayList;
    }

    @Override
    public List<IRolle> loadRoller() {
        iMapper = new RolleMapper();
        List<IRolle> rolleList = new ArrayList<>();
        for(Object object: iMapper.getAllObjects()){
            IRolle iRolle = (IRolle) object;
            rolleList.add(iRolle);
        }
        return rolleList;
    }

    @Override
    public Map<String, IBruger> loadbrugere() {
        iMapper = new BrugerMapper();
        Map<String, IBruger> brugerMap = new HashMap<>();
        for(Object object : iMapper.getAllObjects()){
            IBruger bruger = (IBruger) object;
            brugerMap.put(bruger.getBrugernavn(), bruger);

        }
        return brugerMap;
    }



    @Override
    public boolean saveCatalogObject(ICatalogObject iCatalogObject) {

        iMapper = null;
        if(iCatalogObject instanceof IPerson){
            IPerson iPerson = (IPerson) iCatalogObject;
            iMapper = new PersonMapper();
            return iMapper.putObject(iPerson);

        }
        else if(iCatalogObject instanceof IProgram){
            IProgram iProgram = (IProgram) iCatalogObject;
            iMapper = new ProgramMapper();
            return iMapper.putObject(iProgram);
        }
        else if(iCatalogObject instanceof IRolle){
            IRolle iRolle = (IRolle) iCatalogObject;
            iMapper = new RolleMapper();
            return iMapper.putObject(iRolle);
        }

        return false;

    }

    public static void main(String[] args) {
        /*
        ProgramMapper programMapper = new ProgramMapper();
        Program program = new Program();

        program.setUdgivelsesDato(new Date());
        program.setProgramNavn("hello");
        program.setProgramType("dokumentar");
        program.setGenre("action");
        program.setProduktionsID(10);
        program.setLængde(2.20);
        programMapper.putObject(program);
        */
        ProgramMapper programMapper = new ProgramMapper();
        for(Object object: programMapper.getAllObjects()){
            Program program = (Program) object;
            System.out.println(program);
            System.out.println(program.getCredits());
        }



    }

    @Override
    public boolean updateCatalogObject(String key, ICatalogObject catalogObject) {
        return false;
    }

    @Override
    public void updateBruger(String key, IBruger iBruger) {

    }


}
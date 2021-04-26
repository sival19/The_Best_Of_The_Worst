package persistancy.database;

import Intefaces.*;
import domain.creditManagement.CatalogObject;

import java.util.List;
import java.util.Map;

public class DatabaseManager implements IDataManager {

    private static DatabaseManager databaseManager;

    private DatabaseManager() {

    }

    public static DatabaseManager getDatabase(){
        if(databaseManager == null){
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }
    @Override
    public IDataBruger loadBruger(String brugerNavn) {
        return null;
    }

    @Override
    public boolean saveBruger(IDataBruger bruger) {
        return false;
    }

    @Override
    public List<IDataPerson> loadPersoner() {
        return null;
    }

    @Override
    public List<IDataProgram> loadProgrammer() {
        return null;
    }

    @Override
    public List<IDataRolle> loadRoller() {
        return null;
    }

    @Override
    public Map<String, IDataBruger> loadbrugere() {
        return null;
    }

    @Override
    public boolean saveCatalogObject(ICatalogObject catalogObject) {
        return false;
    }

    @Override
    public boolean updateCatalogObject(String key, ICatalogObject catalogObject) {
        return false;
    }

    @Override
    public boolean updateBruger(String key, IDataBruger iDataBruger) {
        return false;
    }
}
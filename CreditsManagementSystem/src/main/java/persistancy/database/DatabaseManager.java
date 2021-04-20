package persistancy.database;

import Intefaces.*;
import domain.creditManagement.CatalogObject;

import java.util.List;

public class DatabaseManager implements IDataManager {

    public DatabaseManager() {

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
    public boolean saveCatalogObject(ICatalogObject catalogObject) {
        return false;
    }

    @Override
    public boolean updateCatalogObject(String key, ICatalogObject catalogObject) {
        return false;
    }
}
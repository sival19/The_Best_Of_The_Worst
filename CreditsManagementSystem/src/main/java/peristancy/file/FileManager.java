package peristancy.file;

import domain.creditManagement.CatalogObject;
import peristancy.IDataBruger;

public class FileManager implements IFileManager {

    public FileManager() {

    }

    public void save(CatalogObject catalogObject) {

    }

    public void load(CatalogObject catalogObject) {

    }

    @Override
    public IDataBruger loadBruger(String brugerNavn) {
        return null; //TODO LOAD BRUGER I FIL, MULIGVIS JSON, MULIGVIS SERIALISABLE OBJEKT
    }

    @Override
    public boolean saveBruger(IDataBruger bruger) {
        //TODO IF FILESAVING WORKS RETURN TRUE
        //TODO ELSE RETURN FALSE
        return false;
    }

}
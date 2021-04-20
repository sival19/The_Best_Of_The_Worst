package Intefaces;

import java.util.List;

public interface IDataManager {

    IDataBruger loadBruger(String brugerNavn);
    boolean saveBruger(IDataBruger bruger);
    List<IDataPerson> loadPersoner();
    List<IDataProgram> loadProgrammer();
    List<IDataRolle> loadRoller();

    boolean saveCatalogObject(ICatalogObject catalogObject);
    boolean updateCatalogObject(String key, ICatalogObject catalogObject);
}

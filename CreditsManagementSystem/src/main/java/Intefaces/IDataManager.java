package Intefaces;

import java.util.List;
import java.util.Map;

public interface IDataManager {

    IDataBruger loadBruger(String brugerNavn);
    boolean saveBruger(IDataBruger bruger);
    List<IDataPerson> loadPersoner();
    List<IDataProgram> loadProgrammer();
    List<IDataRolle> loadRoller();
    Map<String,IDataBruger> loadbrugere();
    boolean saveCatalogObject(ICatalogObject catalogObject);
    boolean updateCatalogObject(String key, ICatalogObject catalogObject);
    boolean updateBruger(String key, IDataBruger iDataBruger);
}

package persistancy;

import Intefaces.*;

import java.util.List;
import java.util.Map;

public interface IDataManager {

    IBruger loadBruger(String brugerNavn);
    boolean saveBruger(IBruger bruger);
    List<IPerson> loadPersoner();
    List<IProgram> loadProgrammer();
    List<IRolle> loadRoller();
    Map<String, IBruger> loadbrugere();
    boolean saveCatalogObject(ICatalogObject catalogObject);
    boolean updateCatalogObject(String key, ICatalogObject catalogObject);
    void updateBruger(String key, IBruger iBruger);
}

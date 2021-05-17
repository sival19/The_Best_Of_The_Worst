package persistancy;

import Intefaces.*;

import java.util.List;
import java.util.Map;

public interface IDataManager {

    IBruger loadBruger(String brugerNavn);
    List<IPerson> loadPersoner();
    List<IProgram> loadProgrammer();
    List<IRolle> loadRoller();
    Map<String, IBruger> loadbrugere();
    boolean saveObject(Object object);
    boolean updateObject(String key, Object object);
}


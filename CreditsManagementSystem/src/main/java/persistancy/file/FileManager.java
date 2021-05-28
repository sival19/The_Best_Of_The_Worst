package persistancy.file;

import Intefaces.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import persistancy.IDataManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager implements IDataManager {
    private static FileManager fileManager;
    File brugerFile;
    File personFile;
    File programFile;
    File rolleFile;

    private FileManager() {
        isSaveFileFolderCheck();
        brugerFile = new File("saveFiles\\brugerFile.json");
        personFile = new File("saveFiles\\personFile.json");
        programFile = new File("saveFiles\\programFile.json");
        rolleFile = new File("saveFiles\\rolleFile.json");

    }

    public static FileManager getInstance() {
        if (fileManager == null) {
            fileManager = new FileManager();
        }
        return fileManager;
    }

    void isSaveFileFolderCheck() {
        File file = new File("saveFiles");
        if (!file.exists()) {
            file.mkdir();
        }
    }


    @Override
    public IBruger loadBruger(String brugerNavn) {
        ObjectMapper objectMapper = new ObjectMapper();
        IBruger iBruger = null;
        Map<String, IBruger> brugerMap = null;
        try {
            if (brugerFile.length() != 0) {
                brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>() {
                });
                iBruger = brugerMap.get(brugerNavn);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iBruger;
    }

    @Override
    public Map<String, IBruger> loadbrugere() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, IBruger> iDataBrugerMap = new HashMap<>();

        if (brugerFile.length() != 0) {
            try {
                iDataBrugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return iDataBrugerMap;
    }


    @Override
    public boolean saveObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {


            if (object instanceof IPerson) {
                IPerson iPerson = (IPerson) object;
                List<IPerson> iPersonList = new ArrayList<>();
                if (personFile.length() != 0) {
                    iPersonList = objectMapper.readValue(personFile, new TypeReference<List<IPerson>>() {
                    });

                }
                iPersonList.add(iPerson);
                objectMapper.writeValue(personFile, iPersonList);
            } else if (object instanceof IProgram) {
                IProgram iProgram = (IProgram) object;
                List<IProgram> iProgramList = new ArrayList<>();

                if (programFile.length() != 0) {
                    iProgramList = objectMapper.readValue(programFile, new TypeReference<List<IProgram>>() {
                    });
                }

                iProgramList.add(iProgram);
                objectMapper.writeValue(programFile, iProgramList);
            } else if (object instanceof IRolle) {
                IRolle iRolle = (IRolle) object;
                List<IRolle> iRolleList = new ArrayList<>();

                if (rolleFile.length() != 0) {
                    iRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IRolle>>() {
                    });
                }

                iRolleList.add(iRolle);
                objectMapper.writeValue(rolleFile, iRolleList);
            } else if (object instanceof IBruger) {
                Map<String, IBruger> brugerMap = new HashMap<>();
                IBruger iBruger = (IBruger) object;
                if (brugerFile.length() != 0) {
                    brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>() {
                    });
                }

                brugerMap.put(iBruger.getBrugernavn(), iBruger);
                objectMapper.writeValue(brugerFile, brugerMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateObject(String key, Object object) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (object instanceof IPerson) {
                IPerson iPerson = (IPerson) object;
                List<IPerson> iPersonList = objectMapper.readValue(personFile, new TypeReference<List<IPerson>>() {
                });
                for (int i = 0; i < iPersonList.size(); i++) {
                    if (iPersonList.get(i).getPersonID() == Integer.parseInt(key)) {
                        iPersonList.set(i, iPerson);
                    }
                }
                objectMapper.writeValue(personFile, iPersonList);
            } else if (object instanceof IProgram) {
                IProgram iProgram = (IProgram) object;
                List<IProgram> iProgramList = objectMapper.readValue(programFile, new TypeReference<List<IProgram>>() {
                });
                for (int i = 0; i < iProgramList.size(); i++) {
                    if (iProgramList.get(i).getProduktionsID() == Integer.parseInt(key)) {
                        iProgramList.set(i, iProgram);
                    }
                }
                objectMapper.writeValue(programFile, iProgramList);
            } else if (object instanceof IRolle) {
                IRolle iRolle = (IRolle) object;
                List<IRolle> iRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IRolle>>() {
                });
                for (int i = 0; i < iRolleList.size(); i++) {
                    if (iRolleList.get(i).getRolleID() == Integer.parseInt(key)) {
                        iRolleList.set(i, iRolle);
                    }
                }
                objectMapper.writeValue(rolleFile, iRolleList);
            } else if (object instanceof IBruger) {
                IBruger iBruger = (IBruger) object;

                Map<String, IBruger> iDataBrugermap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>() {
                });
                iDataBrugermap.replace(key, iBruger);
                objectMapper.writeValue(brugerFile, iDataBrugermap);

            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<IPerson> loadPersoner() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IPerson> iPersonList = null;
        try {
            if (personFile.length() != 0) {
                iPersonList = objectMapper.readValue(personFile, new TypeReference<List<IPerson>>() {
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iPersonList;
    }

    @Override
    public List<IProgram> loadProgrammer() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IProgram> iProgramList = null;
        try {
            if (programFile.length() != 0) {
                iProgramList = objectMapper.readValue(programFile, new TypeReference<List<IProgram>>() {
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iProgramList;
    }

    @Override
    public List<IRolle> loadRoller() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IRolle> iRolleList = new ArrayList<>();
        try {
            if (rolleFile.length() != 0) {
                iRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IRolle>>() {
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iRolleList;
    }


}
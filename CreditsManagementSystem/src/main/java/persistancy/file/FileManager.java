package persistancy.file;

import Intefaces.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.credits.*;

import java.io.*;
import java.util.*;

public class FileManager implements IDataManager {
    File brugerFile;
    File personFile;
    File programFile;
    File rolleFile;
    private  static FileManager fileManager;

    private FileManager() {
        isSaveFileFolderCheck();
        brugerFile = new File("saveFiles\\brugerFile.json");
        personFile = new File("saveFiles\\personFile.json");
        programFile = new File("saveFiles\\programFile.json");
        rolleFile = new File("saveFiles\\rolleFile.json");

    }

    public static FileManager getFileManager() {
        if(fileManager== null){
            fileManager = new FileManager();
        }
        return fileManager;
    }

    void isSaveFileFolderCheck(){
        File file = new File("saveFiles");
        if(!file.exists()){
            file.mkdir();
        }
    }

    @Override
    public boolean saveBruger(IDataBruger iDatabruger) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, IDataBruger> brugerMap = new HashMap<>();
        try {
            if(brugerFile.length()!=0){
                brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IDataBruger>>() {});
            }

            brugerMap.put(iDatabruger.getBrugernavn(),iDatabruger);
            objectMapper.writeValue(brugerFile,brugerMap);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public IDataBruger loadBruger(String brugerNavn) {
        ObjectMapper objectMapper = new ObjectMapper();
        IDataBruger iBruger = null;
        Map<String, IDataBruger> brugerMap = null;
        try {
            if(brugerFile.length() != 0){
                brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IDataBruger>>(){});
                iBruger = brugerMap.get(brugerNavn);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iBruger;
    }

    @Override
    public Map<String,IDataBruger> loadbrugere() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,IDataBruger> iDataBrugerMap = null;
        try {
            if(brugerFile.length() != 0){
                iDataBrugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IDataBruger>>() {
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iDataBrugerMap;
    }

    @Override
    public boolean updateBruger(String key, IDataBruger iDataBruger) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String , IDataBruger> iDataBrugermap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IDataBruger>>() {});
            iDataBrugermap.replace(key,iDataBruger);
            objectMapper.writeValue(brugerFile,iDataBrugermap);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveCatalogObject(ICatalogObject icatalogObject) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            if(icatalogObject instanceof IDataPerson){
                IDataPerson iDataPerson = (IDataPerson) icatalogObject;
                List<IDataPerson> iDataPersonList = new ArrayList<>();
                if(personFile.length() != 0){
                    iDataPersonList = objectMapper.readValue(personFile, new TypeReference<List<IDataPerson>>(){});

                }
                iDataPersonList.add(iDataPerson);
                objectMapper.writeValue(personFile,iDataPersonList);
            }
            else if(icatalogObject instanceof IDataProgram){
                IDataProgram iDataProgram = (IDataProgram) icatalogObject;
                List<IDataProgram> iDataProgramList = new ArrayList<>();

                if(programFile.length() != 0){
                    iDataProgramList = objectMapper.readValue(programFile, new TypeReference<List<IDataProgram>>(){});
                }

                iDataProgramList.add(iDataProgram);
                System.out.println(iDataProgramList);
                objectMapper.writeValue(programFile,iDataProgramList);
            }
            else if(icatalogObject instanceof IDataRolle){
                IDataRolle iDataRolle = (IDataRolle) icatalogObject;
                List<IDataRolle> iDataRolleList = new ArrayList<>();

                if(rolleFile.length() != 0){
                    iDataRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IDataRolle>>(){});
                }

                iDataRolleList.add(iDataRolle);
                objectMapper.writeValue(rolleFile,iDataRolleList);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCatalogObject(String key, ICatalogObject icatalogObject) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(icatalogObject instanceof IDataPerson){
                IDataPerson iDataPerson = (IDataPerson) icatalogObject;
                List<IDataPerson> iDataPersonList = objectMapper.readValue(personFile, new TypeReference<List<IDataPerson>>(){});
                for(int i = 0; i<iDataPersonList.size(); i++){
                    if(iDataPersonList.get(i).getPersonID() == Integer.parseInt(key)){
                        iDataPersonList.set(i,iDataPerson);
                    }
                }
                objectMapper.writeValue(personFile,iDataPersonList);
            }
            else if(icatalogObject instanceof IDataProgram){
                IDataProgram iDataProgram = (IDataProgram) icatalogObject;
                List<IDataProgram> iDataProgramList = objectMapper.readValue(programFile, new TypeReference<List<IDataProgram>>(){});
                for (int i = 0; i<iDataProgramList.size(); i++) {
                    if (iDataProgramList.get(i).getProduktionsID() == Integer.parseInt(key)) {
                        iDataProgramList.set(i,iDataProgram);
                    }
                }
                System.out.println(iDataProgramList.size());
                objectMapper.writeValue(programFile,iDataProgramList);
            }
            else if(icatalogObject instanceof IDataRolle){
                IDataRolle iDataRolle = (IDataRolle) icatalogObject;
                List<IDataRolle> iDataRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IDataRolle>>(){});
                for(int i = 0; i<iDataRolleList.size(); i++){
                    if(iDataRolleList.get(i).getRolleID() == Integer.parseInt(key)){
                        iDataRolleList.set(i,iDataRolle);
                    }
                }
                objectMapper.writeValue(rolleFile,iDataRolleList);
            }


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<IDataPerson> loadPersoner() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IDataPerson> iDataPersonList = null;
        try {
            if(personFile.length() != 0){
                iDataPersonList = objectMapper.readValue(personFile, new TypeReference<List<IDataPerson>>() {});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iDataPersonList;
    }

    @Override
    public List<IDataProgram> loadProgrammer() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IDataProgram> iDataProgramList = null;
        try {
            if(programFile.length() != 0){
                iDataProgramList = objectMapper.readValue(programFile, new TypeReference<List<IDataProgram>>() {});
            }

         //   System.out.println(iDataProgramList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iDataProgramList;
    }

    @Override
    public List<IDataRolle> loadRoller() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IDataRolle> iDataRolleList = new ArrayList<>();
        try {
            if(rolleFile.length() != 0){
                iDataRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IDataRolle>>(){});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iDataRolleList;
    }


    public static void main(String[] args) throws IOException {
/*
        Scanner scanner1 = new Scanner(new File(FileManager.class.getResource("brugerFile.json").getPath()));
        String jsonString = programFile.json;
        while (scanner1.hasNext()){
            jsonString = scanner1.nextLine();
        }
        String brugernavn, String adgangskode, String email, Rettighed rettighed

ObjectMapper objectMapper = new ObjectMapper();
        Bruger bruger = new Bruger("123dfsf","sdssdasd","fujam20@student.sdu.sdk",Rettighed.ADMINISTRATOR,1);
        Map<String, IDataBruger> brugerMap = new HashMap<>();
        brugerMap.put(bruger.getBrugernavn(),bruger);

        objectMapper.writeValue(new File("brugerFile.json")
                ,brugerMap);*/

 /*

        saveBrugertest(bruger);
        IDataBruger iDataBruger = loadBrugerTest("123dfsf");
        System.out.println(iDataBruger);


 objectMapper = new ObjectMapper();
        Map<String, IDataBruger> brugerMap1 = programFile.json;
        try {
            brugerMap1 = objectMapper.readValue(new File(FileManager.class.getResource("brugerFile.json").getFile()), new TypeReference<Map<String, IDataBruger>>() {});//objectMapper.getTypeFactory().constructMapType(HashMap.class,String.class, IDataBruger.class)
        } catch (IOException e) {
            e.printStackTrace();
        }
        IDataBruger iDataBruger = brugerMap1.get("123dfsf");
        System.out.println(iDataBruger.getBrugernavn());
        objectMapper.writeValue(/*
                new File(FileManager.class.getResource("brugerFile.json").getFile()),brugerMap1);

        FileManager fileManager = new FileManager();
        List<IDataPerson> personList = fileManager.loadPersoner();
        System.out.println(personList);
        IDataPerson person1 = programFile.json;
        for(IDataPerson person : personList){
            if(person.getPersonID() == 5){
                person1 = person;
            }
        }

        person1.setNavn("Sigurd");


        fileManager.updateCatalogObject("5",person1);
        fileManager.loadPersoner();*/
/*
        IDataBruger Idatabruger = fileManager.loadBruger("123dfsf");
        System.out.println(Idatabruger);
        fileManager.saveBruger(Idatabruger);
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writeValue();
        List<Credit> creditList = new ArrayList<>();
        creditList.add(new Credit(new Person("Kasper",new Date(),"Danmark",10),new Rolle("Producer",3),"HovedInstruktør for hele produktionen."));
        FileManager fileManager = new FileManager();
        fileManager.saveCatalogObject(new Program("Pirates of the caribian",5,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));

     List<Credit> creditList = new ArrayList<>();
        creditList.add(new Credit(new Person("Kasper",new Date(),"Danmark",10),new Rolle("Producer",3),"HovedInstruktør for hele produktionen."));
        List<Program> programs = new ArrayList<>();
        programs.add(new Program("Pirates of the caribian",5,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        programs.add(new Program("TED",5,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        programs.add(new Program("Twighlight",5,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        programs.add(new Program("The Way of the HouseHusband",5,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(programFile,programs);
        } catch (IOException e) {
            e.printStackTrace();
        }


        FileManager fileManager = new FileManager();
        fileManager.saveCatalogObject(new Rolle("Producer",1));
        fileManager.saveCatalogObject(new Rolle("Skuespiller",2));
        fileManager.saveCatalogObject(new Rolle("LydMand",3));
        System.out.println(fileManager.loadRoller());
        List<Rolle> rolleList = new ArrayList<>();
        rolleList.add(new Rolle("Producer",1));
        rolleList.add(new Rolle("Skuespiller",2));
        rolleList.add(new Rolle("LydMand",3));
        FileManager fileManager = new FileManager();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue( fileManager.rolleFile,rolleList);

  ObjectMapper objectMapper = new ObjectMapper();
        List<IDataProgram> iDataProgramList = new ArrayList<>();
        objectMapper.writeValue(new FileManager().programFile,iDataProgramList);


        List<Credit> creditList = new ArrayList<>();
        creditList.add(new Credit(new Person("Kasper",new Date(),"Danmark",10),new Rolle("Producer",3),"HovedInstruktør for hele produktionen."));
        List<Program> programs = new ArrayList<>();
        programs.add(new Program("Pirates of the caribian",1,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        programs.add(new Program("TED",2,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        programs.add(new Program("Twighlight",3,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        programs.add(new Program("The Way of the HouseHusband",4,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));

        FileManager fileManager = new FileManager();
        fileManager.saveCatalogObject(new Program("Pirates of the caribian",1,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        fileManager.saveCatalogObject(new Program("TED",2,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        fileManager.saveCatalogObject(new Program("Twighlight",3,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        fileManager.saveCatalogObject(new Program("The Way of the HouseHusband",4,new Date(),ProgramType.FILM,Genre.ACTION,1.40,creditList));
        FileManager fileManager = new FileManager();

        fileManager.saveCatalogObject(new Rolle("Producer",1));
        fileManager.saveCatalogObject(new Rolle("Skuespiller",2));
        fileManager.saveCatalogObject(new Rolle("LydMand",3)); System.out.println(fileManager.loadRoller());
*/

     /*
        List<Rolle> rolleList = new ArrayList<>();
        rolleList.add(new Rolle("Producer",1));
        rolleList.add(new Rolle("Skuespiller",2));
        rolleList.add(new Rolle("LydMand",3));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("rolleFile.json"),rolleList);
        } catch (IOException e) {
            e.printStackTrace();
        }








        FileManager fileManager = new FileManager();
        fileManager.saveCatalogObject(new Rolle("Producer",1));
        fileManager.saveCatalogObject(new Rolle("Skuespiller",2));
        fileManager.saveCatalogObject(new Rolle("LydMand",3));


*/
//        List<IDataPerson> iDataPersonList = new ArrayList<>();
//        iDataPersonList.add(new Person("Jim",new Date(),"USA",1));
//        iDataPersonList.add(new Person("Tim",new Date(),"Australia",2));
//        iDataPersonList.add(new Person("Simon",new Date(),"Danmark",3));
//        iDataPersonList.add(new Person("JernHört",new Date(),"Sweden",4));
//        new ObjectMapper().writeValue(new File("personFile.json"),iDataPersonList);

    }


}
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
    public IBruger loadBruger(String brugerNavn) {
        ObjectMapper objectMapper = new ObjectMapper();
        IBruger iBruger = null;
        Map<String, IBruger> brugerMap = null;
        try {
            if(brugerFile.length() != 0){
                brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>(){});
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
        Map<String, IBruger> iDataBrugerMap = null;
        try {
            if(brugerFile.length() != 0){
                iDataBrugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>() {
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iDataBrugerMap;
    }



    @Override
    public boolean saveObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {


            if(object instanceof IPerson){
                IPerson iPerson = (IPerson) object;
                List<IPerson> iPersonList = new ArrayList<>();
                if(personFile.length() != 0){
                    iPersonList = objectMapper.readValue(personFile, new TypeReference<List<IPerson>>(){});

                }
                iPersonList.add(iPerson);
                objectMapper.writeValue(personFile, iPersonList);
            }
            else if(object instanceof IProgram){
                IProgram iProgram = (IProgram) object;
                List<IProgram> iProgramList = new ArrayList<>();

                if(programFile.length() != 0){
                    iProgramList = objectMapper.readValue(programFile, new TypeReference<List<IProgram>>(){});
                }

                iProgramList.add(iProgram);
                System.out.println(iProgramList);
                objectMapper.writeValue(programFile, iProgramList);
            }
            else if(object instanceof IRolle){
                IRolle iRolle = (IRolle) object;
                List<IRolle> iRolleList = new ArrayList<>();

                if(rolleFile.length() != 0){
                    iRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IRolle>>(){});
                }

                iRolleList.add(iRolle);
                objectMapper.writeValue(rolleFile, iRolleList);
            }
            else if(object instanceof IBruger){
                Map<String,IBruger> brugerMap= new HashMap<>();
                IBruger iBruger = (IBruger) object;
                if(brugerFile.length()!=0){
                    brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>() {});
                }

                brugerMap.put(iBruger.getBrugernavn(),iBruger);
                objectMapper.writeValue(brugerFile,brugerMap);
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
            if(object instanceof IPerson){
                IPerson iPerson = (IPerson) object;
                List<IPerson> iPersonList = objectMapper.readValue(personFile, new TypeReference<List<IPerson>>(){});
                for(int i = 0; i< iPersonList.size(); i++){
                    if(iPersonList.get(i).getPersonID() == Integer.parseInt(key)){
                        iPersonList.set(i, iPerson);
                    }
                }
                objectMapper.writeValue(personFile, iPersonList);
            }
            else if(object instanceof IProgram){
                IProgram iProgram = (IProgram) object;
                List<IProgram> iProgramList = objectMapper.readValue(programFile, new TypeReference<List<IProgram>>(){});
                for (int i = 0; i< iProgramList.size(); i++) {
                    if (iProgramList.get(i).getProduktionsID() == Integer.parseInt(key)) {
                        iProgramList.set(i, iProgram);
                    }
                }
                System.out.println(iProgramList.size());
                objectMapper.writeValue(programFile, iProgramList);
            }
            else if(object instanceof IRolle){
                IRolle iRolle = (IRolle) object;
                List<IRolle> iRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IRolle>>(){});
                for(int i = 0; i< iRolleList.size(); i++){
                    if(iRolleList.get(i).getRolleID() == Integer.parseInt(key)){
                        iRolleList.set(i, iRolle);
                    }
                }
                objectMapper.writeValue(rolleFile, iRolleList);
            }

            else if(object instanceof IBruger){
                IBruger iBruger = (IBruger) object;

                Map<String , IBruger> iDataBrugermap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IBruger>>() {});
                iDataBrugermap.replace(key, iBruger);
                objectMapper.writeValue(brugerFile,iDataBrugermap);

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
            if(personFile.length() != 0){
                iPersonList = objectMapper.readValue(personFile, new TypeReference<List<IPerson>>() {});
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
            if(programFile.length() != 0){
                iProgramList = objectMapper.readValue(programFile, new TypeReference<List<IProgram>>() {});
            }

         //   System.out.println(iProgramList.size());

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
            if(rolleFile.length() != 0){
                iRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IRolle>>(){});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iRolleList;
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
        Map<String, IBruger> brugerMap = new HashMap<>();
        brugerMap.put(bruger.getBrugernavn(),bruger);

        objectMapper.writeValue(new File("brugerFile.json")
                ,brugerMap);*/

 /*

        saveBrugertest(bruger);
        IBruger iDataBruger = loadBrugerTest("123dfsf");
        System.out.println(iDataBruger);


 objectMapper = new ObjectMapper();
        Map<String, IBruger> brugerMap1 = programFile.json;
        try {
            brugerMap1 = objectMapper.readValue(new File(FileManager.class.getResource("brugerFile.json").getFile()), new TypeReference<Map<String, IBruger>>() {});//objectMapper.getTypeFactory().constructMapType(HashMap.class,String.class, IBruger.class)
        } catch (IOException e) {
            e.printStackTrace();
        }
        IBruger iDataBruger = brugerMap1.get("123dfsf");
        System.out.println(iDataBruger.getBrugernavn());
        objectMapper.writeValue(/*
                new File(FileManager.class.getResource("brugerFile.json").getFile()),brugerMap1);

        FileManager fileManager = new FileManager();
        List<IPerson> personList = fileManager.loadPersoner();
        System.out.println(personList);
        IPerson person1 = programFile.json;
        for(IPerson person : personList){
            if(person.getPersonID() == 5){
                person1 = person;
            }
        }

        person1.setNavn("Sigurd");


        fileManager.updateCatalogObject("5",person1);
        fileManager.loadPersoner();*/
/*
        IBruger Idatabruger = fileManager.loadBruger("123dfsf");
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
        List<IProgram> iDataProgramList = new ArrayList<>();
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
//        List<IPerson> iDataPersonList = new ArrayList<>();
//        iDataPersonList.add(new Person("Jim",new Date(),"USA",1));
//        iDataPersonList.add(new Person("Tim",new Date(),"Australia",2));
//        iDataPersonList.add(new Person("Simon",new Date(),"Danmark",3));
//        iDataPersonList.add(new Person("JernHört",new Date(),"Sweden",4));
//        new ObjectMapper().writeValue(new File("personFile.json"),iDataPersonList);

    }


}
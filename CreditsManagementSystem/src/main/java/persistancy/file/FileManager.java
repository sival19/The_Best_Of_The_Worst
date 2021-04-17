package persistancy.file;

import Intefaces.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.credits.Person;
import domain.credits.Rolle;
import domain.logIn.Bruger;
import domain.logIn.Rettighed;

import java.io.*;
import java.util.*;

public class FileManager implements IFileManager {
    File brugerFile;
    File personFile;
    File programFile;
    File rolleFile;

    public FileManager() {
        brugerFile = new File(FileManager.class.getResource("brugerFile.json").getFile());
        personFile = new File(FileManager.class.getResource("personFile.json").getFile());
        programFile = new File(FileManager.class.getResource("programFile.json").getFile());
        rolleFile = new File(FileManager.class.getResource("rolleFile.json").getFile());
    }

    @Override
    public boolean saveBruger(IDataBruger iDatabruger) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, IDataBruger> brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IDataBruger>>() {});
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
        try {
            Map<String, IDataBruger> brugerMap = objectMapper.readValue(brugerFile, new TypeReference<Map<String, IDataBruger>>(){});
            iBruger = brugerMap.get(brugerNavn);
            objectMapper.writeValue(brugerFile,brugerMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iBruger;
    }

    @Override
    public boolean saveCatalogObject(ICatalogObject icatalogObject) {

        IDataPerson person = new Person("Fuad",new Date(),"Danmark",1);
        IDataPerson person1 = new Person("Simon",new Date(),"Tyskland",2);
        IDataPerson person2 = new Person("Dennis",new Date(),"Oestrig",3);
        IDataPerson person3 = new Person("Niels",new Date(),"Spanien",4);
        List<IDataPerson> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(personFile,personList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(icatalogObject instanceof IDataPerson){
                IDataPerson iDataPerson = (IDataPerson) icatalogObject;
                List<IDataPerson> iDataPersonList = objectMapper.readValue(personFile, new TypeReference<List<IDataPerson>>(){});
                iDataPersonList.add(iDataPerson);
                objectMapper.writeValue(personFile,iDataPersonList);
            }
            else if(icatalogObject instanceof IDataProgram){
                IDataProgram iDataProgram = (IDataProgram) icatalogObject;
                List<IDataProgram> iDataProgramList = objectMapper.readValue(programFile, new TypeReference<List<IDataProgram>>(){});
                iDataProgramList.add(iDataProgram);
                objectMapper.writeValue(programFile,iDataProgramList);
            }
            else if(icatalogObject instanceof IDataRolle){
                IDataRolle iDataRolle = (IDataRolle) icatalogObject;
                List<IDataRolle> iDataRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IDataRolle>>(){});
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
                        iDataPersonList.add(i,iDataPerson);
                    }
                }
                objectMapper.writeValue(personFile,iDataPersonList);
            }
            else if(icatalogObject instanceof IDataProgram){
                IDataProgram iDataProgram = (IDataProgram) icatalogObject;
                List<IDataProgram> iDataProgramList = objectMapper.readValue(programFile, new TypeReference<List<IDataProgram>>(){});
                iDataProgramList.add(iDataProgram);
                objectMapper.writeValue(programFile,iDataProgramList);
            }
            else if(icatalogObject instanceof IDataRolle){
                IDataRolle iDataRolle = (IDataRolle) icatalogObject;
                List<IDataRolle> iDataRolleList = objectMapper.readValue(rolleFile, new TypeReference<List<IDataRolle>>(){});
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
    public List<IDataPerson> loadPersoner() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IDataPerson> iDataPersonList = null;
        try {
             iDataPersonList = objectMapper.readValue(new File(FileManager.class.getResource("personFile.json").getFile()),objectMapper.getTypeFactory().constructCollectionType(List.class,IDataPerson.class));

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
            iDataProgramList = objectMapper.readValue(new File(FileManager.class.getResource("programFile.json").getFile()),objectMapper.getTypeFactory().constructCollectionType(List.class,IDataProgram.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iDataProgramList;
    }

    @Override
    public List<IDataRolle> loadRoller() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IDataRolle> iDataRolleList = null;
        try {
            iDataRolleList = objectMapper.readValue(new File(FileManager.class.getResource("rolleFile.json").getFile()),objectMapper.getTypeFactory().constructCollectionType(List.class,IDataRolle.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iDataRolleList;
    }




    public static void main(String[] args) throws IOException {
/*
        Scanner scanner1 = new Scanner(new File(FileManager.class.getResource("brugerFile.json").getPath()));
        String jsonString = null;
        while (scanner1.hasNext()){
            jsonString = scanner1.nextLine();
        }
        String brugernavn, String adgangskode, String email, Rettighed rettighed

*/
ObjectMapper objectMapper = new ObjectMapper();
        Bruger bruger = new Bruger("123dfsf","sdssdasd","fujam20@student.sdu.sdk",Rettighed.ADMINISTRATOR,1);
        Map<String, IDataBruger> brugerMap = new HashMap<>();
        brugerMap.put(bruger.getBrugernavn(),bruger);

        objectMapper.writeValue(
                new File(FileManager.class.getResource("brugerFile.json").getFile()),brugerMap);
 /*

        saveBrugertest(bruger);
        IDataBruger iDataBruger = loadBrugerTest("123dfsf");
        System.out.println(iDataBruger);


 objectMapper = new ObjectMapper();
        Map<String, IDataBruger> brugerMap1 = null;
        try {
            brugerMap1 = objectMapper.readValue(new File(FileManager.class.getResource("brugerFile.json").getFile()), new TypeReference<Map<String, IDataBruger>>() {});//objectMapper.getTypeFactory().constructMapType(HashMap.class,String.class, IDataBruger.class)
        } catch (IOException e) {
            e.printStackTrace();
        }
        IDataBruger iDataBruger = brugerMap1.get("123dfsf");
        System.out.println(iDataBruger.getBrugernavn());
        objectMapper.writeValue(/*
                new File(FileManager.class.getResource("brugerFile.json").getFile()),brugerMap1);
*/
        FileManager fileManager = new FileManager();
        fileManager.saveCatalogObject(new Person("Thomas",new Date(),"Danmark",5));
        System.out.println(fileManager.loadPersoner());
/*
        IDataBruger Idatabruger = fileManager.loadBruger("123dfsf");
        System.out.println(Idatabruger);
        fileManager.saveBruger(Idatabruger);
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writeValue();*/




    }


}
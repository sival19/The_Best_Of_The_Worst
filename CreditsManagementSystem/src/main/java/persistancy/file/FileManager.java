package persistancy.file;

import Intefaces.IFileManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.creditManagement.CatalogObject;
import domain.logIn.Bruger;
import domain.logIn.Rettighed;
import Intefaces.IDataBruger;
import org.json.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager implements IFileManager {

    public FileManager() {

    }

    public void save(CatalogObject catalogObject) {

    }

    public void load(CatalogObject catalogObject) {

    }

    @Override
    public IDataBruger loadBruger(String brugerNavn) {
        ObjectMapper objectMapper = new ObjectMapper();
        IDataBruger iBruger = null;
        try {
            Map<String, IDataBruger> brugerMap = objectMapper.readValue(new File(FileManager.class.getResource("brugerfile.json").getFile()),objectMapper.getTypeFactory().constructMapType(HashMap.class,String.class,IDataBruger.class));
            iBruger = brugerMap.get(brugerNavn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iBruger;
    }

    @Override
    public boolean saveBruger(IDataBruger iDatabruger) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, IDataBruger> brugerMap = objectMapper.readValue(new File(FileManager.class.getResource("brugerfile.json").getFile()),objectMapper.getTypeFactory().constructMapType(HashMap.class,String.class,IDataBruger.class));
            brugerMap.put(iDatabruger.getBrugernavn(),iDatabruger);

            objectMapper.writeValue(new File(FileManager.class.getResource("brugerfile.json").getFile()),brugerMap);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void main(String[] args){
/*
        Scanner scanner1 = new Scanner(new File(FileManager.class.getResource("brugerfile.json").getPath()));
        String jsonString = null;
        while (scanner1.hasNext()){
            jsonString = scanner1.nextLine();
        }
        String brugernavn, String adgangskode, String email, Rettighed rettighed




        Map<String, IDataBruger> brugerMap = new HashMap<>();
        brugerMap.put(bruger.getBrugernavn(),bruger);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new File(FileManager.class.getResource("brugerfile.json").getFile()),brugerMap);
        Map<String, IDataBruger> brugerMap1 = objectMapper.readValue(new File(FileManager.class.getResource("brugerfile.json").getFile()),objectMapper.getTypeFactory().constructMapType(HashMap.class,String.class,IDataBruger.class));
        IDataBruger iDataBruger = brugerMap1.get(bruger.getBrugernavn());
        System.out.println(iDataBruger.getBrugernavn());
    Bruger bruger = new Bruger("123dfsf","sdssdasd","fujam20@student.sdu.sdk",Rettighed.ADMINISTRATOR,1);
        saveBrugertest(bruger);
        IDataBruger iDataBruger = loadBrugerTest("123dfsf");
        System.out.println(iDataBruger);
*/
    }

}
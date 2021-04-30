package domain.logIn;

import Intefaces.IBruger;
import Intefaces.IDataManager;
import persistancy.file.FileManager;

import java.util.*;

public class UserManager {

    private Bruger bruger;
    private IDataManager fileManager;

    public UserManager() {
        fileManager = FileManager.getFileManager();
        bruger = new Bruger();
        bruger.setRettighed(Rettighed.SEER);
    }


    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed){
        String result = "";
        Rettighed brugerRettighed = null;

        Map<String, IBruger> brugerMap = fileManager.loadbrugere();
        int indeks;
        if(brugerMap!=null){
           indeks = brugerMap.size()+1;
        }
        else {
            indeks = 1;
        }



        if(rettighed.equals("Administrator")){
            brugerRettighed = Rettighed.ADMINISTRATOR;
        }
        else if(rettighed.equals("Producer")){
            brugerRettighed = Rettighed.PRODUCER;
        }
        if(isBruger(brugernavn)){
            result = "Bruger eksisterer";
        }

        else if(!fileManager.saveBruger(new Bruger(brugernavn,adgangskode,email,brugerRettighed,indeks))){
            result = "Kunne ikke gemmes";
        }

        else if(fileManager.saveBruger(new Bruger(brugernavn,adgangskode,email,brugerRettighed,indeks))){
            result = "Kunne gemmes";
        }

        System.out.println("hello");
        return result;

    }

    public String validereBruger(String brugernavn, String adgangskode) {
        String result = "";
        Bruger bruger = (Bruger) fileManager.loadBruger(brugernavn);

        if(bruger == null){
            result = "Bruger eksisterer ikke";
        }
        else if (!bruger.getAdgangskode().equals(adgangskode)){
            result = "Adgangskode er forkert";
        }
        else if (bruger.getAdgangskode().equals(adgangskode)){
            this.bruger = bruger;
            result = "Velkommen!";
            this.bruger = bruger;
        }
        System.out.println(this.bruger.getRettighed());
        return result;
    }

    public Bruger getBruger() {
        return bruger;
    }

    public boolean isBruger(String brugernavn) {
        return fileManager.loadBruger(brugernavn) != null;
    }

//if admin true else false
    public boolean isAdmin(){
        return bruger.getRettighed() == Rettighed.ADMINISTRATOR;
    }
    public boolean isProducer(){
        return bruger.getRettighed() == Rettighed.PRODUCER;
    }



    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.opretBruger("33","33","sds","Producer");
    }

}

package domain.logIn;

import Factory.CreditManagementSystemFactory;
import Factory.DataManagementFactory;
import Intefaces.IDataManager;

public class UserManager {

    private Bruger bruger;
    private IDataManager fileManager;

    public UserManager() {
        fileManager = DataManagementFactory.createDataManager("file");
        bruger = new Bruger();
        bruger.setRettighed(Rettighed.BESOEGER);

    }

    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed){
        String result = "";
        Rettighed brugerRettighed = null;

        if(rettighed.equals("Administrator")){
            brugerRettighed = Rettighed.ADMINISTRATOR;
        }
        else if(rettighed.equals("Producer")){
            brugerRettighed = Rettighed.PRODUCER;
        }
        if(isBruger(brugernavn)){
            result = "Bruger eksister";
        }

        else if(!fileManager.saveBruger(new Bruger(brugernavn,adgangskode,email,brugerRettighed,1))){
            result = "Kunne ikke gemmes";
        }

        return result;

    }

    public String validereBruger(String brugernavn, String adgangskode) {
        String result = "";
        Bruger bruger = (Bruger) fileManager.loadBruger(brugernavn);

        if(bruger == null){
            result = "bruger eksister ikke";
        }
        else if (!bruger.getAdgangskode().equals(adgangskode)){
            result = "adgangskode er forkert";
        }
        else if (bruger.getAdgangskode().equals(adgangskode)){
            result = "Welkommen!";
        }
        return result;
    }

    public boolean isBruger(String brugernavn) {
        return fileManager.loadBruger(brugernavn) != null;
    }

    public boolean isAdmin(){
        return bruger.getRettighed() == Rettighed.ADMINISTRATOR;
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.opretBruger("33","33","sds","Producer");

    }

}

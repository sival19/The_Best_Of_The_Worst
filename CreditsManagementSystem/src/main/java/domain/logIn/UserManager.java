package domain.logIn;

import Factory.CreditManagementSystemFactory;
import peristancy.file.IFileManager;

public class UserManager {

    private Bruger bruger;
    private IFileManager fileManager;

    public UserManager() {
        fileManager = CreditManagementSystemFactory.createFileManager();

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

        else if(!fileManager.saveBruger(new Bruger(brugernavn,adgangskode,email,brugerRettighed))){
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
        else if (!bruger.getAdgangsKode().equals(adgangskode)){
            result = "adgangskode er forkert";
        }
        else if (bruger.getAdgangsKode().equals(adgangskode)){
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

}

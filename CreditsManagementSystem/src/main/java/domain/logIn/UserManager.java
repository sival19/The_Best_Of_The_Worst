package domain.logIn;

import Intefaces.IBruger;
import persistancy.IDataManager;
import persistancy.database.DatabaseManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserManager {

    private Bruger bruger;

    private IDataManager iDataManager;

    public UserManager() {
        iDataManager = new DatabaseManager();

        bruger = new Bruger();
        bruger.setRettighed(Rettighed.SEER);
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.opretBruger("33", "33", "sds", "Producer");
    }

    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed) {
        String result = "";
        Rettighed brugerRettighed = null;


        Map<String, IBruger> brugerMap = iDataManager.loadbrugere();



        int indeks;
        if (brugerMap != null) {
            indeks = brugerMap.size() + 1;
        } else {
            indeks = 1;
        }

        if (rettighed.equals("Administrator")) {
            brugerRettighed = Rettighed.ADMINISTRATOR;
        } else if (rettighed.equals("Producer")) {
            brugerRettighed = Rettighed.PRODUCER;
        }
        if (isBruger(brugernavn)) {
            result = "Bruger eksisterer";
        }
        boolean sucess = iDataManager.saveBruger(new Bruger(brugernavn, adgangskode, email, brugerRettighed, indeks));


        if (!sucess) {
            result = "Kunne ikke gemmes";
        } else if (sucess) {
            result = "Kunne gemmes";
        }

        return result;

    }

    public String validereBruger(String brugernavn, String adgangskode) {
        String result = "";

        Bruger bruger = (Bruger) iDataManager.loadBruger(brugernavn);

        if (bruger == null) {
            result = "Bruger eksisterer ikke";
        } else if (!bruger.getAdgangskode().equals(adgangskode)) {
            result = "Adgangskode er forkert";
        } else if (bruger.getAdgangskode().equals(adgangskode)) {
            this.bruger = bruger;
            result = "Velkommen!";
        }
        System.out.println(this.bruger.getRettighed());
        return result;
    }

    public Bruger getBruger() {
        return bruger;
    }

    public Collection<IBruger> getBrugere(){
        return iDataManager.loadbrugere().values();
    }

    public boolean isBruger(String brugernavn) {

        return iDataManager.loadBruger(brugernavn) != null;

    }

    //if admin true else false
    public boolean isAdmin() {
        return bruger.getRettighed() == Rettighed.ADMINISTRATOR;
    }

    public boolean isProducer(){
        return bruger.getRettighed() == Rettighed.PRODUCER;
    }

    public void updateBruger(){
        iDataManager.updateBruger(bruger.getBrugernavn(), bruger);
    }




}

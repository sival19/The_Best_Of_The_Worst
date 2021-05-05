package domain.creditManagement;

import Intefaces.*;
import domain.credits.Person;
import domain.credits.Program;
import domain.credits.Rolle;
import domain.logIn.UserManager;
import persistancy.database.DatabaseManager;
import persistancy.file.FileManager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditsManagementSystem implements ICreditsManagementSystem {
    private UserManager userManager;
    private Catalog catalog;
    private static CreditsManagementSystem creditsManagementSystem;



    private CreditsManagementSystem() {
        userManager = new UserManager();
        catalog = new Catalog();
    }

    public static ICreditsManagementSystem getCreditManagementSystem(){
        if(creditsManagementSystem == null){
            creditsManagementSystem = new CreditsManagementSystem();
        }
        return creditsManagementSystem;
    }


    @Override
    public List<IProgram> getPrograms() {
        return new ArrayList<>(catalog.getProgrammer().values());
    }

    @Override
    public List<IPerson> getPersons() {
        return new ArrayList<>(catalog.getPersoner().values());
    }

    @Override
    public List<IRolle> getRoller() {
        return new ArrayList<>(catalog.getRoller().values());
    }

    @Override
    public IProgram getProgram() {
        return catalog.getProgram();
    }

    @Override
    public IPerson getPerson() {
        return catalog.getPerson();
    }

    @Override
    public IRolle getRolle() {
        return catalog.getRolle();
    }

    @Override
    public void setRolle(IRolle iRolle) {
        catalog.setRolle((Rolle) iRolle);
    }

    @Override
    public void setProgram(IProgram iProgram) {
        catalog.setProgram((Program) iProgram);
    }

    @Override
    public void setPerson(IPerson iPerson) {
        catalog.setPerson((Person) iPerson);
    }

    @Override
    public String getBrugerrettighed() {
        return userManager.getBruger().getRettighed().toString();
    }

    @Override
    public String opretCredit(String produktionsID, String rolletype, String personID, String beskrivelse) {
        return catalog.opretCredit(personID,rolletype, produktionsID, beskrivelse);
    }

    public String opretPerson(String navn, String nationalitet, String fødselsdato) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(fødselsdato);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date);
        return catalog.opretPerson(navn, nationalitet, date);
    }



    public String opretRolle(String rolletype) {
        return catalog.opretRolle(rolletype);
    }




    @Override
    public List<ICatalogObject> søgCredit(String søgeord) {
        return new ArrayList<>(catalog.søg(søgeord));
    }

    @Override
    public boolean isAdmin() {
        return userManager.isAdmin();
    }
    @Override
    public boolean isProducer() {
        return userManager.isProducer();
    }

    @Override
    public IBruger getBruger() {
        return userManager.getBruger();
    }


    @Override
    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed) {
        return userManager.opretBruger(brugernavn, adgangskode, email, rettighed);
    }

    @Override
    public String opretProgram(String programnavn,String yr, String mth,String programtype, String genre, double længde) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = simpleDateFormat.parse(yr+"-"+mth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(catalog.opretProgram(programnavn,date,programtype,genre,længde)) {
            List<Integer> produktionsIDer = new ArrayList<>();
            produktionsIDer.add(catalog.getProgrammer().size());
            userManager.getBruger().setProduktionsIDer(produktionsIDer);
            userManager.updateBruger();

            return "Program er oprettet";

        }
        return "Program er ikke oprettet";
    }

    @Override
    public String login(String brugernavn, String adgangskode) {
        return userManager.validereBruger(brugernavn, adgangskode);
    }

    public static void main(String[] args) {
        CreditsManagementSystem creditsManagementSystem = new CreditsManagementSystem();
        creditsManagementSystem.opretProgram("vild","2001","01","dokumentar","action",2.0);

    }
}


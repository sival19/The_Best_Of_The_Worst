package domain.creditManagement;

import Factory.DataManagementFactory;
import Intefaces.*;
import domain.credits.ProgramType;
import domain.credits.Rolle;
import domain.logIn.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditsManagementSystem implements ICreditsManagementSystem {
    private UserManager userManager;
    private Catalog catalog;
    private IDataManager iFileManager;
    private IDataProgram program;
    private IDataPerson person;
    private IDataRolle rolle;
    private static CreditsManagementSystem creditsManagementSystem;



    public CreditsManagementSystem() {
        userManager = new UserManager();
        catalog = new Catalog();
        iFileManager = DataManagementFactory.createDataManager("file");

    }


    @Override
    public List<IDataProgram> getPrograms() {
        return new ArrayList<>(catalog.getProgrammer().values());
    }

    @Override
    public List<IDataPerson> getPersons() {
        return new ArrayList<>(catalog.getPersoner().values());
    }

    @Override
    public List<IDataRolle> getRoller() {
        return new ArrayList<>(catalog.getRoller().values());
    }

    @Override
    public IDataProgram getProgram() {
        return program;
    }

    @Override
    public IDataPerson getPerson() {
        return person;
    }

    @Override
    public IDataRolle getRolle() {
        return rolle;
    }

    @Override
    public void setRolle(IDataRolle iDataRolle) {
        rolle = iDataRolle;
    }

    @Override
    public void setProgram(IDataProgram iDataProgram) {
        program = iDataProgram;
    }

    @Override
    public void setPerson(IDataPerson iDataPerson) {
        person = iDataPerson;
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
        return null;
    }

    public String opretProgram(String programNavn, int produktionsID, Date udgivelsesDato, ProgramType programType, String genre, double længde) {
        return null;
    }

    public String seProgram(int produktionsID) {
        return null;
    }

    public String sePerson(int personID) {
        return null;
    }

    public String seRolle(Rolle rolletype) {
        return null;
    }

    public String søgCredit(String søgeord) {
        return null;
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
    public IDataBruger getBruger() {
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
            date = simpleDateFormat.parse(yr + "-" +mth );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        if(catalog.opretProgram(programnavn,date,programtype,genre,længde)) {
            return "Program er oprettet";
        }
        return "Program er ikke oprettet";
    }

    @Override
    public String login(String brugernavn, String adgangskode) {
        return userManager.validereBruger(brugernavn, adgangskode);
    }
}

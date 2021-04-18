package domain.creditManagement;

import Factory.CreditManagementSystemFactory;
import Intefaces.*;
import domain.credits.ProgramType;
import domain.credits.Rolle;
import domain.logIn.UserManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditsManagementSystem implements ICreditsManagementSystem {
    private UserManager userManager;
    private Catalog catalog;
    private IFileManager iFileManager;


    public CreditsManagementSystem() {
        userManager = new UserManager();
        catalog = new Catalog();
        iFileManager = CreditManagementSystemFactory.getFileManager();

    }

    public String opretCredit(String produktionsID, String rolletype, String personID, String beskrivelse) {
        return catalog.opretCredit(personID,rolletype, produktionsID, beskrivelse);
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
    public List<IDataRolle> getRolle() {
        return new ArrayList<>(catalog.getRoller().values());
    }

    public String opretPerson(String navn, String nationalitet, Date fødselsdato) {
        return null;
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
    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed) {
        return userManager.opretBruger(brugernavn, adgangskode, email, rettighed);
    }

    @Override
    public String login(String brugernavn, String adgangskode) {
        return userManager.validereBruger(brugernavn, adgangskode);
    }
}
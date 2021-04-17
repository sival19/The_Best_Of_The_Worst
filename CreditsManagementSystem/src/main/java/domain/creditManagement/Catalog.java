package domain.creditManagement;

import Factory.CreditManagementSystemFactory;
import Intefaces.IDataPerson;
import Intefaces.IDataProgram;
import Intefaces.IDataRolle;
import Intefaces.IFileManager;
import domain.credits.Person;
import domain.credits.Program;
import domain.credits.ProgramType;
import domain.credits.Rolle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {

    Map<String, Program> programmer;
    Map<String, Rolle> roller;
    Map<String, Person> personer;


    public Catalog() {
        personer = new HashMap<>();
        roller = new HashMap<>();
        programmer = new HashMap<>();
        IFileManager iFileManager = CreditManagementSystemFactory.createFileManager();

        List<IDataPerson> tempPers =  iFileManager.loadPersoner();
        List<IDataProgram> tempPro = iFileManager.loadProgrammer();
        List<IDataRolle> tempRol = iFileManager.loadRoller();

        fillCatalog(tempPers,tempPro,tempRol);

    }

    private void fillCatalog(List<IDataPerson> personer, List<IDataProgram> programs, List<IDataRolle> roller){
        for(IDataPerson person: personer){
            this.personer.put(String.valueOf(person.getPersonID()), new Person(person.getNavn(),person.getFoedselsdato(),person.getNationalitet(),person.getPersonID()));
        }

        for(IDataProgram program: programs){
            this.programmer.put(String.valueOf(program.getProduktionsID()), new Program(program.getProgramNavn(),program.getProduktionsID(),program.getUdgivelsesDato(),program.getProgramType(),program.getGenre(),program.getLængde(),program.getCredits()));
        }

        for(IDataRolle rolle: roller){
            this.roller.put(String.valueOf(rolle.getRolletype()), new Rolle(rolle.getRolletype(),rolle.getRolleID()));
        }

    }

    public void addToList(Person person) {

    }

    public void addToList(Program program) {

    }

    public void opretPerson(String navn, String nationalitet, Date fødselsdato, int personID) {

    }

    public void opretProgram(String programNavn, Date udgivelssdato, ProgramType programtype, String genre, double længde) {

    }

    public void getAllPersonCredits(Person person) {

    }

    public void getAllRolleCredits(Rolle rolletype) {

    }

    public void søg(String søgeord) {

    }

    public Person getPerson(int personID){
        return personer.get(String.valueOf(personID));
    }
    public Rolle getRolle(String rolleType){
        return roller.get(String.valueOf(rolleType));
    }
    public Program getProgram(int produktionID){
        return programmer.get(String.valueOf(produktionID));
    }

}
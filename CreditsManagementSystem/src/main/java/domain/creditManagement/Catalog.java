package domain.creditManagement;

import Factory.CreditManagementSystemFactory;
import Factory.DataManagementFactory;
import Intefaces.*;
import domain.credits.Person;
import domain.credits.Program;
import domain.credits.ProgramType;
import domain.credits.Rolle;

import java.util.*;

public class Catalog {

    Map<String, Program> programmer;
    Map<String, Rolle> roller;
    Map<String, Person> personer;
    IDataManager iFileManager;

    public Catalog() {
        personer = new HashMap<>();
        roller = new HashMap<>();
        programmer = new HashMap<>();
        iFileManager = DataManagementFactory.createDataManager("file");

        List<IDataPerson> tempPers =  iFileManager.loadPersoner();
        List<IDataProgram> tempPro = iFileManager.loadProgrammer();
        List<IDataRolle> tempRol = iFileManager.loadRoller();

        fillCatalog(tempPers,tempPro,tempRol);
        System.out.println(getProgrammer());
        System.out.println(getPersoner());
        System.out.println(getRoller());

    }

    private void fillCatalog(List<IDataPerson> personer, List<IDataProgram> programs, List<IDataRolle> roller){
        if(personer!= null){
            for(IDataPerson iDataPerson: personer){
                Person person = new Person(iDataPerson.getNavn(),iDataPerson.getFoedselsdato(),iDataPerson.getNationalitet(),iDataPerson.getPersonID());
                if(iDataPerson.getImagePath()!= null) {
                    person.setImagePath(iDataPerson.getImagePath());
                }
                else{
                    person.setImagePath("defaultMovieImage.jpg");
                }
                this.personer.put(String.valueOf(person.getPersonID()), person);

            }
        }

        if(programs!= null){
            for(IDataProgram iDataProgram: programs){
                Program program = new Program(iDataProgram.getProgramNavn(),iDataProgram.getProduktionsID(),iDataProgram.getUdgivelsesDato(),iDataProgram.getProgramType(),iDataProgram.getGenre(),iDataProgram.getLængde(),iDataProgram.getCredits());
                if(iDataProgram.getImagePath()!= null) {
                    program.setImagePath(iDataProgram.getImagePath());
                }
                else{
                    program.setImagePath("defaultMovieImage.jpg");
                }
                this.programmer.put(String.valueOf(program.getProduktionsID()), program);

            }
        }


        if(roller!=null){
            for(IDataRolle iDataRolle: roller){
                Rolle rolle = new Rolle(iDataRolle.getRolletype(),iDataRolle.getRolleID());
                if(iDataRolle.getImagePath()!= null) {
                    rolle.setImagePath(iDataRolle.getImagePath());
                }
                else{
                    rolle.setImagePath("defaultMovieImage.jpg");
                }
                this.roller.put(String.valueOf(rolle.getRolletype()), rolle);
            }
        }


    }

    public String opretCredit(String personID, String rolletype, String produktionsID, String beskrivelse ){
        Program program = programmer.get(produktionsID);
        Person person = personer.get(personID);
        Rolle rolle = roller.get(rolletype);

        StringBuilder stringBuilder = new StringBuilder();
        if(program == null){
            return "Program: " + produktionsID + " Findes ikke";
        }
        else if(person == null){
            return "Person: " + personID + "Findes ikke";
        }
        else if(rolle == null){
            return "Rolle: " + produktionsID + " Findes ikke";        }

        program.opretCredit(person,rolle,beskrivelse);
        programmer.replace(String.valueOf(program.getProduktionsID()),program);
        iFileManager.updateCatalogObject(String.valueOf(program.getProduktionsID()),program);
        List<IDataProgram> programList =iFileManager.loadProgrammer();
        System.out.println(programList  );

        return "Credit er oprettet";


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

    public Map<String, Program> getProgrammer() {
        return programmer;
    }

    public Map<String, Person> getPersoner() {
        return personer;
    }

    public Map<String, Rolle> getRoller() {
        return roller;
    }

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.programmer.get("1").setImagePath("piratesnew.png");
        catalog.programmer.get("2").setImagePath("ted.jpg");
        catalog.programmer.get("3").setImagePath("twighlight.jpg");
        catalog.programmer.get("4").setImagePath("househusband.jpg");
        catalog.iFileManager.updateCatalogObject("1",catalog.programmer.get("1"));
        catalog.iFileManager.updateCatalogObject("2",catalog.programmer.get("2"));
        catalog.iFileManager.updateCatalogObject("3",catalog.programmer.get("3"));
        catalog.iFileManager.updateCatalogObject("4",catalog.programmer.get("4"));

        System.out.println(catalog.iFileManager.loadProgrammer());
    }
}
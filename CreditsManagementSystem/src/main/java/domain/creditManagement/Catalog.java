package domain.creditManagement;

import Intefaces.*;
import domain.credits.*;
import hub.Hub;

import java.util.*;

public class Catalog {

    private Map<String, Program> programmer;
    private Map<String, Rolle> roller;
    private Map<String, Person> personer;
    private Program program;
    private Person person;
    private Rolle rolle;
    IDataManager iFileManager;
    IHub hub;

    public Catalog() {
        hub = new Hub();
        personer = new HashMap<>();
        roller = new HashMap<>();
        programmer = new HashMap<>();
        iFileManager = hub.getDataManager("file");

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

        Rolle rolle = roller.get(rolletype.toLowerCase());
        System.out.println(program);
        System.out.println(person);
        System.out.println(rolle);

        if(program == null){
            return "Program: " + produktionsID + " Findes ikke";
        }
        else if(person == null){
            return "Person: " + personID + "Findes ikke";
        }
        else if(rolle == null){
            return "Rolle: " + rolletype + " Findes ikke";        }

       else if( program.opretCredit(person,rolle,beskrivelse)){
           programmer.replace(String.valueOf(program.getProduktionsID()),program);
           iFileManager.updateCatalogObject(String.valueOf(program.getProduktionsID()),program);
           List<IDataProgram> programList =iFileManager.loadProgrammer();
           System.out.println(programList  );

           return "Credit er oprettet";
       }
       else {
           return "Credit eksister allerede";
       }



    }

    public Program getProgram() {
        return program;
    }

    public Person getPerson() {
        return person;
    }


    public Rolle getRolle() {
        return rolle;
    }


    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public String opretPerson(String navn, String nationalitet, Date fødselsdato) {
        String result = "";
        int indeks;
        if(personer!=null){
            indeks = personer.size()+1;
        }
        else {
            indeks = 1;
        }
        Person person = new Person(navn, fødselsdato, nationalitet, indeks);
        boolean saveSucces= iFileManager.saveCatalogObject(person);

        if(!saveSucces){
            result = "Kunne ikke gemmes";
        }

        else if(saveSucces){
            result = "Kunne gemmes";
            personer.put(String.valueOf(person.getPersonID()),person);
        }

        return result;
    }


    public boolean opretProgram(String programNavn, Date udgivelssdato, String programtype, String genre, double længde) {
        ProgramType programTypetemp = null;
        if(ProgramType.DOKUMENTAR.toString().equalsIgnoreCase(programtype)){
            programTypetemp = ProgramType.DOKUMENTAR;

        }
        else if(ProgramType.FILM.toString().equalsIgnoreCase(programtype)){
            programTypetemp = ProgramType.FILM;

        }
        else if(ProgramType.KORTFILM.toString().equalsIgnoreCase(programtype)){
            programTypetemp = ProgramType.KORTFILM;

        }
        else if(ProgramType.SERIE.toString().equalsIgnoreCase(programtype)){
            programTypetemp = ProgramType.SERIE;

        }
        Genre genretemp = null;
        if(Genre.ACTION.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.ACTION;

        }
        else if(Genre.ADVENTURE.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.ADVENTURE;

        }
        else if(Genre.COMEDY.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.COMEDY;

        }
        else if(Genre.CRIME.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.CRIME;

        }
        else if(Genre.HORROR.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.HORROR;

        }
        else if(Genre.ROMANCE.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.ROMANCE;

        }
        else if(Genre.SCIFI.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.SCIFI;

        }
        else if(Genre.DRAMA.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.DRAMA;

        }
        else if(Genre.FANTASY.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.FANTASY;

        }
        else if(Genre.THRILLER.toString().equalsIgnoreCase(genre)){
            genretemp = Genre.THRILLER;

        }
        int indeks;
        if(programmer!=null){
            indeks = programmer.size()+1;
        }
        else {
            indeks= 1;
        }
        Program program = new Program(programNavn, indeks, udgivelssdato,programTypetemp,genretemp,længde, new ArrayList<Credit>());
        programmer.put(String.valueOf(program.getProduktionsID()),program);

        return iFileManager.saveCatalogObject(program);

    }
    public boolean isPerson(int personID) {
        if(iFileManager.loadPersoner().size()>= personID){
            return true;
        } else{
            return false;
        }
    }


    public void getAllPersonCredits(Person person) {

    }

    public void getAllRolleCredits(Rolle rolletype) {

    }

    public List<CatalogObject> søg(String søgeord) {
        ArrayList<CatalogObject> catalogObjects = new ArrayList<>();
        for (Person person : personer.values()) {
            if (person.getNavn().toLowerCase().contains(søgeord.toLowerCase())) {
                catalogObjects.add(person);
            }
        }

        // Search for a person by ID
        for (Person person : personer.values()) {
            if (søgeord.equals(String.valueOf(person.getPersonID()))) {
                catalogObjects.add((person));
            }
        }

        // Search for a program by name
        for (Program program : programmer.values()) {
            if (program.getProgramNavn().toLowerCase().contains(søgeord.toLowerCase())) {
                catalogObjects.add(program);
            }
        }
        // Search for a role by role type
        for (Rolle rolle : roller.values()) {
            if (rolle.getRolletype().toLowerCase().contains(søgeord.toLowerCase())) {
                catalogObjects.add(rolle);
            }
        }
        return catalogObjects;

    }

    public Person getPerson(int personID){
        return personer.get(String.valueOf(personID));
    }
    public Rolle getRolle(String rolleType){
        return roller.get(String.valueOf(rolleType));
    }
    public String opretRolle(String rolletype){
        int indeks;
        if(roller!=null){
            indeks = roller.size()+1;
        }
        else {
            indeks = 1;
        }

        Rolle rolle = new Rolle(rolletype.toLowerCase(), indeks);
        if(iFileManager.saveCatalogObject(rolle)){
            roller.put(rolletype,rolle);
            return "Rolle er oprettet";
        }
        return "Rolle er ikke oprettet";
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
        catalog.programmer.get("0").setImagePath("piratesnew.png");
        catalog.programmer.get("1").setImagePath("ted.jpg");
        catalog.programmer.get("2").setImagePath("twighlight.jpg");
        catalog.iFileManager.updateCatalogObject("0",catalog.programmer.get("0"));
        catalog.iFileManager.updateCatalogObject("1",catalog.programmer.get("1"));
        catalog.iFileManager.updateCatalogObject("2",catalog.programmer.get("2"));


        System.out.println(catalog.iFileManager.loadProgrammer());
    }
}
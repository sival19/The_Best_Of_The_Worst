package domain.credits;

import Intefaces.*;
import persistancy.IDataManager;
import persistancy.database.DatabaseManager;

import java.util.*;


public class Catalog {

    private Map<String, Program> programmer; //The program catalog
    private Map<String, Rolle> roller; //the rolle catalog
    private Map<String, Person> personer;//The person catalog
    private Program program;//The current program clicked on
    private Person person;//The current person clicked on
    private Rolle rolle;//The current rolle clicked on
    private IDataManager iDataManager;

    public Catalog() {
        personer = new HashMap<>();
        roller = new HashMap<>();
        programmer = new HashMap<>();
        iDataManager = new DatabaseManager();

        List<IPerson> tempPers =  iDataManager.loadPersoner();
        List<IProgram> tempPro = iDataManager.loadProgrammer();
        List<IRolle> tempRol = iDataManager.loadRoller();

        fillCatalog(tempPers,tempPro,tempRol);
        System.out.println(getProgrammer());
        System.out.println(getPersoner());
        System.out.println(getRoller());

    }

    private void fillCatalog(List<IPerson> personer, List<IProgram> programs, List<IRolle> roller){
        if(personer!= null){
            for(IPerson iPerson : personer){
                Person person = new Person(iPerson.getNavn(), iPerson.getFoedselsdato(), iPerson.getNationalitet(), iPerson.getPersonID());
                if(iPerson.getImagePath()!= null) {
                    person.setImagePath(iPerson.getImagePath());
                }
                else{
                    person.setImagePath("defaultMovieImage.jpg");
                }
                this.personer.put(String.valueOf(person.getPersonID()), person);

            }
        }

        if(programs!= null){
            for(IProgram iProgram : programs){
                //Adding the list<ICredit> one by one to list called credits, so i can add to program constructor. The list are different types so have to do this


                Program program = new Program(iProgram.getProgramNavn(), iProgram.getProduktionsID(), iProgram.getUdgivelsesDato(), iProgram.getProgramType(), iProgram.getGenre(), iProgram.getLængde(), iProgram.getCredits());
                if(iProgram.getImagePath()!= null) {
                    program.setImagePath(iProgram.getImagePath());
                }
                else{
                    program.setImagePath("defaultMovieImage.jpg");
                }
                this.programmer.put(String.valueOf(program.getProduktionsID()), program);

            }
        }


        if(roller!=null){
            for(IRolle iRolle : roller){
                Rolle rolle = new Rolle(iRolle.getRolletype(), iRolle.getRolleID());
                if(iRolle.getImagePath()!= null) {
                    rolle.setImagePath(iRolle.getImagePath());
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
           iDataManager.updateObject(String.valueOf(program.getProduktionsID()),program);
           List<IProgram> programList = iDataManager.loadProgrammer();
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
        String confirmation;
        int indeks;

        if(personer!=null){
            indeks = personer.size()+1;
        }
        else {
            indeks = 1;
        }
        boolean saveSucces = false;

        if(!isPerson(indeks)){
            Person person = new Person(navn, fødselsdato, nationalitet, indeks);
            saveSucces= iDataManager.saveObject(person);

            if(!saveSucces){
                result = "Person oprettet\n" +
                        "Kunne ikke gemmes";
            }

            else if(saveSucces){
                result = "Person oprettet\n" +
                        "Kunne gemmes";
                personer.put(String.valueOf(person.getPersonID()),person);
            }
        } else {
            result = "Person eksisterer allerede";
        }


        System.out.println(result);

        return result;
    }


    public boolean opretProgram(String programNavn, Date udgivelssdato,
                                String programtype, String genre, double længde) {

        int indeks;
        if(programmer!=null){
            indeks = programmer.size()+1;
        }
        else {
            indeks= 1;
        }

        Program program = new Program();
        program.setProgramNavn(programNavn);
        program.setUdgivelsesDato(udgivelssdato);
        program.setProgramType(programtype);
        program.setGenre(genre);
        program.setLængde(længde);
        program.setProduktionsID(indeks);
        program.setCredits(new ArrayList<ICredit>());
        programmer.put(String.valueOf(program.getProduktionsID()),program);
        return iDataManager.saveObject(program);

    }
    public boolean isPerson(int personID) {
        if(personer.size()> personID){
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
        if(iDataManager.saveObject(rolle)){
            roller.put(rolletype,rolle);
            return "Rolle er oprettet";
        }
        return "Rolle er ikke oprettet";
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
        catalog.iDataManager.updateObject("1",catalog.programmer.get("1"));
        catalog.iDataManager.updateObject("2",catalog.programmer.get("2"));
        catalog.iDataManager.updateObject("3",catalog.programmer.get("3"));
        catalog.iDataManager.updateObject("4",catalog.programmer.get("4"));

        System.out.println(catalog.iDataManager.loadProgrammer());
    }
}
package domain.credits;

import Intefaces.IDataCredit;

public class Credit implements IDataCredit {

    private Person person;

    private Rolle rolle;

    private String beskrivelse;

    public Credit(Person person, Rolle rolle, String beskrivelse) {

    }

    public Credit(){};

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}

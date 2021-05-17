package domain.credits;

import Intefaces.ICredit;
import Intefaces.IPerson;
import Intefaces.IRolle;

public class Credit implements ICredit {

    private IPerson person;

    private IRolle rolle;

    private String beskrivelse;

    public Credit(Person person, Rolle rolle, String beskrivelse) {
        this.person = person;
        this.rolle = rolle;
        this.beskrivelse = beskrivelse;
    }

    public Credit() {
    }


    public IPerson getPerson() {
        return person;
    }

    public void setPerson(IPerson person) {
        this.person = person;
    }

    public IRolle getRolle() {
        return rolle;
    }

    public void setRolle(IRolle rolle) {
        this.rolle = rolle;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    @Override
    public String toString() {
        return person.getNavn() + ", " +
                rolle.getRolletype() +": "+
                beskrivelse + "\n";
    }
}

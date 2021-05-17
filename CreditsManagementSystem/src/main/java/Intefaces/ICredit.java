package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.Credit;
import domain.credits.Person;
import domain.credits.Rolle;

@JsonDeserialize(as = Credit.class)
public interface ICredit {

    IPerson getPerson();

    void setPerson(IPerson person);

    IRolle getRolle();

    void setRolle(IRolle rolle);

    String getBeskrivelse();
    void setBeskrivelse(String beskrivelse);

}

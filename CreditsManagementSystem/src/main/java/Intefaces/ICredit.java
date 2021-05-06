package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.Credit;
import domain.credits.Person;
import domain.credits.Rolle;

@JsonDeserialize(as = Credit.class)
public interface ICredit {

    Person getPerson();

    void setPerson(Person person);

    Rolle getRolle();

    void setRolle(Rolle rolle);

    String getBeskrivelse();
}

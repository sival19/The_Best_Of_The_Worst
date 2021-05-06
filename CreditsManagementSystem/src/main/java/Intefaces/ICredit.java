package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.Credit;
import domain.credits.Person;
import domain.credits.Rolle;

@JsonDeserialize(as = Credit.class)
public interface ICredit extends ICatalogObject{

    public Person getPerson();

    public void setPerson(Person person);

    public Rolle getRolle();

    public void setRolle(Rolle rolle);
}

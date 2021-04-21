package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.Credit;
import domain.credits.Person;
import domain.credits.Rolle;
import domain.logIn.Bruger;

@JsonDeserialize(as = Credit.class)
public interface IDataCredit extends ICatalogObject{

    public Person getPerson();

    public void setPerson(Person person);

    public Rolle getRolle();

    public void setRolle(Rolle rolle);
}

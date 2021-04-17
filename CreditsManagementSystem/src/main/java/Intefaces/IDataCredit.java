package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.Credit;
import domain.logIn.Bruger;

@JsonDeserialize(as = Credit.class)
public interface IDataCredit extends ICatalogObject{
}

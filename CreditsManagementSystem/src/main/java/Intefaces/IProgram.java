package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.*;

import java.util.Date;
import java.util.List;

@JsonDeserialize(as = Program.class)
public interface IProgram extends ICatalogObject {
    public String getProgramNavn();

    public void setProgramNavn(String programNavn);

    public int getProduktionsID();

    public void setProduktionsID(int produktionsID);

    public Date getUdgivelsesDato();

    public void setUdgivelsesDato(Date udgivelsesDato);

    public ProgramType getProgramType();

    public void setProgramType(String programType);

    public Genre getGenre();

    public void setGenre(String genre);

    public double getLængde();

    public void setLængde(double længde);

    public List<ICredit> getCredits();

    public void setCredits(List<ICredit> credits);

    public boolean opretCredit(Person person, Rolle rolle, String beskrivelse);

    public String getImagePath();

    public void setImagePath(String imagePath);


}

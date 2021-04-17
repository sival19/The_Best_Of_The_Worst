package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.*;

import java.util.Date;
import java.util.List;

@JsonDeserialize(as = Program.class)
public interface IDataProgram extends ICatalogObject {
    public String getProgramNavn();

    public void setProgramNavn(String programNavn);

    public int getProduktionsID();

    public void setProduktionsID(int produktionsID);

    public Date getUdgivelsesDato();

    public void setUdgivelsesDato(Date udgivelsesDato);

    public ProgramType getProgramType();

    public void setProgramType(ProgramType programType);

    public Genre getGenre();

    public void setGenre(Genre genre);

    public double getLængde();

    public void setLængde(double længde);

    public List<Credit> getCredits();

    public void setCredits(List<Credit> credits);

    public boolean opretCredit(Person person, Rolle rolle, String beskrivelse);

    public String getImagePath();

    public void setImagePath(String imagePath);

}

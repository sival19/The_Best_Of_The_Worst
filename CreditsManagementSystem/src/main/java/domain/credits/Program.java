package domain.credits;

import Intefaces.IDataProgram;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;

public class Program implements IDataProgram{

    private String programNavn;

    private int produktionsID;

    private LocalDate udgivelsesDato;

    private ProgramType programType;

    private Genre genre;

    private double længde;

    private List<Credit> credits;

    private String imagePath;


    public Program(String programNavn, int produktionsID, LocalDate udgivelsesDato, ProgramType programType, Genre genre, double længde, List<Credit> credits) {
        this.programNavn = programNavn;
        this.produktionsID = produktionsID;
        this.udgivelsesDato = udgivelsesDato;
        this.programType = programType;
        this.genre = genre;
        this.længde = længde;
        this.credits = credits;
    }

    public Program(){}

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getProgramNavn() {
        return programNavn;
    }

    public void setProgramNavn(String programNavn) {
        this.programNavn = programNavn;
    }

    public int getProduktionsID() {
        return produktionsID;
    }

    public void setProduktionsID(int produktionsID) {
        this.produktionsID = produktionsID;
    }

    public LocalDate getUdgivelsesDato() {
        return udgivelsesDato;
    }

    public void setUdgivelsesDato(LocalDate udgivelsesDato) {
        this.udgivelsesDato = udgivelsesDato;
    }

    public ProgramType getProgramType() {
        return programType;
    }

    public void setProgramType(ProgramType programType) {
        this.programType = programType;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getLængde() {
        return længde;
    }

    public void setLængde(double længde) {
        this.længde = længde;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    @Override
    public boolean opretCredit(Person person, Rolle rolle, String beskrivelse) {
        Credit credit = new Credit(person,rolle,beskrivelse);
        credits.add(credit);
        return true;
    }

    @Override
    public String toString() {
        return   programNavn + " " +udgivelsesDato;
    }

    @JsonIgnore
    public String getCreditListString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Credits: ");
        for(Credit credit: credits){
            stringBuilder.append(credit).append("\n");
        }
        return stringBuilder.toString();
    }
}
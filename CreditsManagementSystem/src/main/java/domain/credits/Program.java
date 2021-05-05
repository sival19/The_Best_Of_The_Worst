package domain.credits;

import Intefaces.IProgram;
import com.fasterxml.jackson.annotation.JsonIgnore;
import domain.creditManagement.CatalogObject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Program extends CatalogObject implements IProgram {

    private String programNavn;

    private int produktionsID;

    private Date udgivelsesDato;

    private ProgramType programType;

    private Genre genre;

    private double længde;

    private List<Credit> credits;

    private String imagePath;


    public Program(String programNavn, int produktionsID, Date udgivelsesDato, ProgramType programType, Genre genre, double længde, List<Credit> credits) {
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

    public Date getUdgivelsesDato() {
        return udgivelsesDato;
    }

    public void setUdgivelsesDato(Date udgivelsesDato) {
        this.udgivelsesDato = udgivelsesDato;
    }

    public ProgramType getProgramType() {
        return programType;
    }

    public void setProgramType(ProgramType programType) {
        this.programType = programType;
    }
    public void setProgramType(String programType) {

        if(ProgramType.DOKUMENTAR.toString().equalsIgnoreCase(programType)){
            this.programType = ProgramType.DOKUMENTAR;

        }
        else if(ProgramType.FILM.toString().equalsIgnoreCase(programType)){
            this.programType = ProgramType.FILM;

        }
        else if(ProgramType.KORTFILM.toString().equalsIgnoreCase(programType)){
            this.programType = ProgramType.KORTFILM;

        }
        else if(ProgramType.SERIE.toString().equalsIgnoreCase(programType)){
            this.programType = ProgramType.SERIE;

        }
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setGenre(String genre) {
        if(Genre.ACTION.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.ACTION;

        }
        else if(Genre.ADVENTURE.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.ADVENTURE;

        }
        else if(Genre.COMEDY.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.COMEDY;

        }
        else if(Genre.CRIME.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.CRIME;

        }
        else if(Genre.HORROR.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.HORROR;

        }
        else if(Genre.ROMANCE.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.ROMANCE;

        }
        else if(Genre.SCIFI.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.SCIFI;

        }
        else if(Genre.DRAMA.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.DRAMA;

        }
        else if(Genre.FANTASY.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.FANTASY;

        }
        else if(Genre.THRILLER.toString().equalsIgnoreCase(genre)){
            this.genre = Genre.THRILLER;

        }
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
        for(Credit credit1: credits){
            if(credit1.getPerson()==person && credit1.getRolle()== rolle){
                return false;
            }
        }
        Credit credit = new Credit(person,rolle,beskrivelse);
        credits.add(credit);
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return   programNavn + " " + simpleDateFormat.format(udgivelsesDato) ;
    }

    @JsonIgnore
    public String getCreditListString(){
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("Credits: ");
        for(Credit credit: credits){
            stringBuilder.append(credit).append("\n");
        }
        return stringBuilder.toString();
    }


}
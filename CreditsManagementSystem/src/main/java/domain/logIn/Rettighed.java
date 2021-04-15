package domain.logIn;

public enum Rettighed {
    ADMINISTRATOR("Administrator"),
    PRODUCER("Producer"),
    BESOEGER("Besoeger");

    private String rettighedWord;

    Rettighed(String rettighedWord){this.rettighedWord = rettighedWord;}
    
}

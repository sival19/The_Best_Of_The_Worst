package domain.logIn;

public enum Rettighed {
    ADMINISTRATOR("Administrator"),
    PRODUCER("Producer"),
    SEER("Seer");

    private String rettighedWord;

    Rettighed(String rettighedWord) {
        this.rettighedWord = rettighedWord;
    }

    @Override
    public String toString() {
        return rettighedWord;
    }
}

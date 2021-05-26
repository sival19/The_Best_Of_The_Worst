package domain.credits;

import Intefaces.ICredit;
import domain.CreditsManagementSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

    Catalog catalog;
    Rolle rolle;
    Person person;
    SimpleDateFormat date;
    Date date2;
    SimpleDateFormat date3 = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        date = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date2 = date.parse("1990-12-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catalog = new Catalog();
        rolle = new Rolle("skuespiller", 1);
        catalog.setRolle(rolle);
        person = new Person("jens", date2, "dansk", 1);
        catalog.setPerson(person);
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void opretRolle(){
        assertNotNull(catalog.getRolle());
    }
    @Test
    void getRolle() {
        assertEquals("skuespiller", catalog.getRolle().getRolletype());
        assertEquals(1, catalog.getRolle().getRolleID());
    }
    @Test
    void getPersoner() {
        assertEquals("jens", catalog.getPerson().getNavn());
        assertEquals("1990-12-10", date3.format(catalog.getPerson().getFoedselsdato()));
        assertEquals("dansk", catalog.getPerson().getNationalitet());
    }
}
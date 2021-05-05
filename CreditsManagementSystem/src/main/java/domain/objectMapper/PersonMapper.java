package domain.objectMapper;

import domain.credits.Person;
import domain.credits.Program;
import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonMapper extends AbstractMapper {
    private DatabaseConnector databaseConnector;
    private PreparedStatement preparedStatement;

    // Constructor that connects the database
    public PersonMapper() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public Object getObject(Object oid) {
        Person person = new Person();
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * from person WHERE person.id = ?");
            preparedStatement.setInt(1, (int)oid);

            ResultSet resultSet = preparedStatement.executeQuery();
            //if since we only want 1 row aka 1 person from the resultset. While loop will keep going to next person if person.id equals oid
            if (resultSet.next()) {
                person.setPersonID(resultSet.getInt("id"));
                person.setNavn(resultSet.getString("navn"));
                person.setFoedselsdato(Date.valueOf(String.valueOf(resultSet.getDate("foedselsdato"))));
                person.setNationalitet(resultSet.getString("nationalitet"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean putObject(Object object) {
        Person person = (Person) object;
        SimpleDateFormat bday = new SimpleDateFormat("yyyy-MM-dd");
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO Person(navn, foedselsdato, nationalitet, id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, person.getNavn());
            preparedStatement.setString(2, bday.format(person.getFoedselsdato()));
            preparedStatement.setString(3, person.getNationalitet());
            preparedStatement.setInt(4, person.getPersonID());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Object> getAllObjects() {
        Person person = new Person();
        List<Object> personList = new ArrayList<>();
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * from person");

            ResultSet resultSet = preparedStatement.executeQuery();
            //if since we only want 1 row aka 1 person from the resultset. While loop will keep going to next person if person.id equals oid
            while (resultSet.next()) {
                person.setPersonID(resultSet.getInt("id"));
                person.setNavn(resultSet.getString("navn"));
                person.setFoedselsdato(Date.valueOf(String.valueOf(resultSet.getDate("foedselsdato"))));
                person.setNationalitet(resultSet.getString("nationalitet"));
                personList.add(person);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }
}

package persistancy.database.objectMapper;

import Intefaces.IPerson;
import Intefaces.IProgram;
import domain.credits.Person;
import persistancy.database.DatabaseConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonMapper implements IMapper {
    private DatabaseConnector databaseConnector;
    private PreparedStatement preparedStatement;

    // Constructor that connects the database
    public PersonMapper() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public Object getObject(Object oid) {
        IPerson person = null;
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * from person WHERE person.id = ?");
            preparedStatement.setInt(1, (int) oid);

            ResultSet resultSet = preparedStatement.executeQuery();
            // If since we only want one row aka one person from the resultset.
            // While loop will keep going to next person if person.id equals oid.
            if (resultSet.next()) {
                person = new Person();
                person.setPersonID(resultSet.getInt("id"));
                person.setNavn(resultSet.getString("person_navn"));
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
        IPerson person = (IPerson) object;
        SimpleDateFormat bday = new SimpleDateFormat("yyyy-MM-dd");
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO Person(person_navn, foedselsdato, nationalitet) VALUES (?,?,?)");
            preparedStatement.setString(1, person.getNavn());
            preparedStatement.setString(2, bday.format(person.getFoedselsdato()));
            preparedStatement.setString(3, person.getNationalitet());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Object> getAllObjects() {

        List<Object> personList = new ArrayList<>();
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * from person");

            ResultSet resultSet = preparedStatement.executeQuery();
            // If since we only want 1 row aka 1 person from the resultset.
            // While loop will keep going to next person if person.id equals oid.
            while (resultSet.next()) {
                IPerson person = new Person();
                person.setPersonID(resultSet.getInt("id"));
                person.setNavn(resultSet.getString("person_navn"));
                person.setFoedselsdato(Date.valueOf(String.valueOf(resultSet.getDate("foedselsdato"))));
                person.setNationalitet(resultSet.getString("nationalitet"));
                personList.add(person);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    @Override
    public boolean updateObject(Object object) {
        return false;
    }
}

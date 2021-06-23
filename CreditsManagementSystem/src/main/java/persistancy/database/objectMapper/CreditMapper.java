package persistancy.database.objectMapper;

import Intefaces.ICredit;
import Intefaces.IPerson;
import Intefaces.IProgram;
import Intefaces.IRolle;
import domain.credits.Credit;
import domain.credits.Person;
import domain.credits.Program;
import domain.credits.Rolle;
import persistancy.database.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CreditMapper implements IMapper {

    private DatabaseConnector databaseConnector;
    private PreparedStatement preparedStatement;

    public CreditMapper() {
        databaseConnector = DatabaseConnector.getInstance();
    }


    @Override
    public Object getObject(Object oid) { // getter et programs fulde liste af credits
        IProgram iProgram = (IProgram) oid;
        List<ICredit> iCreditList = new ArrayList<>();

        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement
                    ("SELECT * FROM credit WHERE program_id = ?");
            // SELECT * FROM credit inner join person p on p.id = credit.person_id
            // inner join program p2 on p2.id = credit.program_id
            // inner join rolle r on r.id = credit.rolle_id
            // WHERE program_id = ?;

            preparedStatement.setInt(1, iProgram.getProduktionsID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                IPerson person = (IPerson) new PersonMapper().getObject(resultSet.getInt("person_id"));
                IRolle rolle = (IRolle) new RolleMapper().getObject(resultSet.getInt("rolle_id"));
                ICredit credit = new Credit();
                credit.setPerson(person);
                credit.setRolle(rolle);
                credit.setBeskrivelse(resultSet.getString("beskrivelse"));
                iCreditList.add(credit);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return iCreditList;
    }

    @Override
    // Adds a programs last credit. Gets called when a program gets updated in the database.
    // If the program's credits does not need updating the method returns false.
    public boolean putObject(Object object) {
        IProgram program = (Program) object;
        ICredit credit = program.getCredits().get(program.getCredits().size() - 1);
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement
                    ("INSERT INTO credit(person_id, rolle_id,beskrivelse, program_id) VALUES (?, ?, ?,?) ");

            preparedStatement.setInt(1, credit.getPerson().getPersonID());
            preparedStatement.setInt(2, credit.getRolle().getRolleID());
            preparedStatement.setString(3, credit.getBeskrivelse());
            preparedStatement.setInt(4, program.getProduktionsID());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }


    @Override
    public List<Object> getAllObjects() {

        ICredit credit = null;
        List<Object> creditList = new ArrayList<>();

        try {
            ResultSet resultSet = preparedStatement.executeQuery();


            // While: Iterates through all the objectes which corrospond to oid.
            // If: Only the first thing credit of the creditlist.
            while (resultSet.next()) {
                // Formatter til date objekt
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                IPerson person = new Person();
                person.setPersonID(resultSet.getInt("person_id"));
                person.setNationalitet(resultSet.getString("nationalitet"));
                person.setNavn(resultSet.getString("navn"));
                try {
                    person.setFoedselsdato(simpleDateFormat.parse(resultSet.getString("foedselsdato")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                IRolle rolle = new Rolle();
                rolle.setRolleID(resultSet.getInt("rolle_id"));
                rolle.setRolletype(resultSet.getString("rolletype"));


                credit = new Credit();
                credit.setPerson(person);
                credit.setBeskrivelse(resultSet.getString("beskrivelse"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        creditList.add(credit);
        return creditList;
    }

    @Override
    public boolean updateObject(Object object) {
        return false;
    }
}

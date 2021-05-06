package domain.objectMapper;

import domain.creditManagement.CreditsManagementSystem;
import domain.credits.Credit;
import domain.credits.Person;
import domain.credits.Program;
import domain.credits.Rolle;
import domain.logIn.UserManager;
import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class ProgramMapper extends AbstractMapper {
    private DatabaseConnector databaseConnector;
    private PreparedStatement preparedStatement;

    public ProgramMapper() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public Object getObject(Object oid) {
        Program program = new Program();
        List<Credit> creditList = new ArrayList<>();
        try {
            //The prepared statement is divided into, the first is for loading the program, the second for the credits.
            //it should be possible to load programs still, if credits are null
             preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * FROM program pr where id = ?");
            preparedStatement.setInt(1,(int)oid);

            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat simpleDateFormatProgram = new SimpleDateFormat("yyyy-MM");

            //if resultset not null
            if(resultSet.next()){
                program.setProgramNavn(resultSet.getString("program_navn"));
                program.setUdgivelsesDato(simpleDateFormatProgram.parse(resultSet.getString("udgivelsesdato")));
                program.setProgramType(resultSet.getString("programtype"));
                program.setGenre(resultSet.getString("genre"));
                program.setProduktionsID(resultSet.getInt("id"));
                program.setLængde(resultSet.getDouble("laengde"));
                program.setImagePath(resultSet.getString("program_image_path"));
                //adds a default empty credit list to programs
                program.setCredits(creditList);

            }

            PreparedStatement preparedStatementcredits = databaseConnector.getConnection().prepareStatement("SELECT * FROM person pe, rolle r, credit c WHERE c.program_id = ? and c.person_id = pe.id and c.rolle_id = r.id");
            preparedStatementcredits.setInt(1,(int)oid);
            ResultSet resultSetCredits = preparedStatementcredits.executeQuery();

            SimpleDateFormat simpleDateFormatPerson = new SimpleDateFormat("yyyy-MM-dd");

            //To get credits if there
            while (resultSetCredits.next()){
                Person person = new Person();
                person.setPersonID(resultSetCredits.getInt("person_id"));
                person.setNavn(resultSetCredits.getString("navn"));
                person.setFoedselsdato(simpleDateFormatPerson.parse(resultSetCredits.getString("foedselsdato")));
                person.setNationalitet(resultSetCredits.getString("nationalitet"));
                person.setImagePath(resultSetCredits.getString("person_image_path"));

                Rolle rolle = new Rolle();
                rolle.setRolleID(resultSetCredits.getInt("rolle_id"));
                rolle.setRolletype(resultSetCredits.getString("rolletype"));
                rolle.setImagePath(resultSetCredits.getString("rolle_image_path"));

                program.getCredits().add(new Credit(person,rolle,resultSetCredits.getString("beskrivelse")));
            }





        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return program;
    }

    @Override
    public boolean putObject(Object object)  {
        Program program = (Program) object;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO program(program_navn, udgivelsesdato, programtype, genre, laengde, program_image_path,bruger_id) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1,program.getProgramNavn());
            preparedStatement.setString(2, simpleDateFormat.format(program.getUdgivelsesDato()));
            preparedStatement.setString(3, program.getProgramType().toString());
            preparedStatement.setString(4,program.getGenre().toString());
            preparedStatement.setDouble(5, program.getLængde());
            preparedStatement.setString(6,program.getImagePath());
            preparedStatement.setInt(7, CreditsManagementSystem.getCreditManagementSystem().getBruger().getBrugerID());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    return true;

    }

    @Override
    public List<Object> getAllObjects() {
        List<Object> programList = new ArrayList<>();
        try {
            //Get all programs first
            PreparedStatement preparedStatement = databaseConnector.getConnection().
                    prepareStatement("SELECT * FROM program");
            ResultSet resultSet = preparedStatement.executeQuery();

            //continue to get programs while the resultlist has a next row
            while (resultSet.next()){
                Program program = new Program();
                List<Credit> creditList = new ArrayList<>();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

                program.setProgramNavn(resultSet.getString("program_navn"));
                program.setUdgivelsesDato(simpleDateFormat.parse(resultSet.getString("udgivelsesdato")));
                program.setProgramType(resultSet.getString("programtype"));
                program.setGenre(resultSet.getString("genre"));
                program.setProduktionsID(resultSet.getInt("id"));
                System.out.println(resultSet.getInt("id"));
                program.setLængde(resultSet.getDouble("laengde"));
                program.setCredits(creditList);
                programList.add(program);


                //get the credits for the chosen program
                PreparedStatement preparedStatementcredits = databaseConnector.getConnection().prepareStatement("SELECT * FROM person pe, rolle r, credit c WHERE c.program_id = ? and c.person_id = pe.id and c.rolle_id = r.id");
                preparedStatementcredits.setInt(1,program.getProduktionsID());
                ResultSet resultSetCredits = preparedStatementcredits.executeQuery();

                SimpleDateFormat simpleDateFormatPerson = new SimpleDateFormat("yyyy-MM-dd");

                //continue to a credit while there is next row
                while (resultSetCredits.next()){
                    Person person = new Person();
                    person.setPersonID(resultSetCredits.getInt("person_id"));
                    person.setNavn(resultSetCredits.getString("navn"));
                    person.setFoedselsdato(simpleDateFormatPerson.parse(resultSetCredits.getString("foedselsdato")));
                    person.setNationalitet(resultSetCredits.getString("nationalitet"));
                    person.setImagePath(resultSetCredits.getString("person_image_path"));

                    Rolle rolle = new Rolle();
                    rolle.setRolleID(resultSetCredits.getInt("rolle_id"));
                    rolle.setRolletype(resultSetCredits.getString("rolletype"));
                    rolle.setImagePath(resultSetCredits.getString("rolle_image_path"));

                    program.getCredits().add(new Credit(person,rolle,resultSetCredits.getString("beskrivelse")));
                }



            }

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return programList;
    }
}


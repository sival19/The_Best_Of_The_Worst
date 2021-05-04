package domain.objectMapper;

import domain.credits.Program;
import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramMapper extends AbstractMapper {
    private DatabaseConnector databaseConnector;
    private PreparedStatement preparedStatement;

    public ProgramMapper() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public Object getObject(int oid) {
        Program program = new Program();
        try {
            PreparedStatement preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * FROM program where id = ?");
            preparedStatement.setInt(1,oid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                program.setProgramNavn(resultSet.getString("program_navn"));
                program.setUdgivelsesDato(Date.valueOf(resultSet.getString("udgivelsesdato")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return program;
    }

    @Override
    public void putObject(Object object)  {
        Program program = (Program) object;
        try {

            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO program(program_navn, udgivelsesdato, programtype, genre, laengde, image_path) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1,program.getProgramNavn());
            preparedStatement.setDate(2, Date.valueOf(program.getUdgivelsesDato().toString()));
            preparedStatement.setString(3, program.getProgramType().toString());
            preparedStatement.setString(4,program.getGenre().toString());
            preparedStatement.setDouble(5, program.getLængde());
            preparedStatement.setString(6,program.getImagePath());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}


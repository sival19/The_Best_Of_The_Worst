package domain.objectMapper;

import domain.credits.Program;
import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramMapper extends AbstractMapper {
    private DatabaseConnector databaseConnector;
    private PreparedStatement preparedStatement;

    public ProgramMapper() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public Object getObject(int oid) {
        // Program program = new Program();
        // databaseConnector.getConnection().prepareStatement("SELECT program_navn ");
        return null;
    }

    @Override
    public void putObject(Object object)  {
        Program program = (Program) object;
        try {

            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO program(program_navn, udgivelsesdato, programtype, genre, laengde, image_path) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1,program.getProgramNavn());
            preparedStatement.setString(2, program.getUdgivelsesDato().toString() );
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


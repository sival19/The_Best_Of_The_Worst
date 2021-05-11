package domain.objectMapper;

import Intefaces.ICredit;
import domain.CreditsManagementSystem;
import domain.credits.Credit;
import domain.credits.Person;
import domain.credits.Program;
import domain.credits.Rolle;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;
import persistancy.database.IMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProgramMapper extends AbstractMapper {
    private DatabaseConnector databaseConnector;
    private PreparedStatement preparedStatement;

    public ProgramMapper() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public Object getObject(Object oid) {
        Program program = null;
        List<ICredit> creditList = new ArrayList<>();
        try {
            // The prepared statement is divided:
            // The first is for loading the program, the second for the credits.
            // It should still be possible to load programs if credits are null.
            preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * FROM program pr where id = ?");
            preparedStatement.setInt(1, (int) oid);

            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat simpleDateFormatProgram = new SimpleDateFormat("yyyy-MM");

            // If resultset is not null.
            if (resultSet.next()) {
                program = new Program();
                program.setProgramNavn(resultSet.getString("program_navn"));
                program.setUdgivelsesDato(simpleDateFormatProgram.parse(resultSet.getString("udgivelsesdato")));
                program.setProgramType(resultSet.getString("programtype"));
                program.setGenre(resultSet.getString("genre"));
                program.setProduktionsID(resultSet.getInt("id"));
                program.setLængde(resultSet.getDouble("laengde"));
                program.setImagePath(resultSet.getString("program_image_path"));
                //adds a default empty credit list to programs
                program.setCredits(creditList);
                IMapper iMapper = new CreditMapper();
                List<ICredit> iCreditList = (List<ICredit>) iMapper.getObject(program);
                if (iCreditList != null) {
                    program.setCredits(iCreditList);
                }
            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return program;
    }

    @Override
    public boolean putObject(Object object) {
        Program program = (Program) object;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO program(program_navn, udgivelsesdato, programtype, genre, laengde, program_image_path,bruger_id) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, program.getProgramNavn());
            preparedStatement.setString(2, simpleDateFormat.format(program.getUdgivelsesDato()));
            preparedStatement.setString(3, program.getProgramType().toString());
            preparedStatement.setString(4, program.getGenre().toString());
            preparedStatement.setDouble(5, program.getLængde());
            preparedStatement.setString(6, program.getImagePath());
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
            preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT * FROM program");
            ResultSet resultSet = preparedStatement.executeQuery();

            //continue to get programs while the resultlist has a next row
            while (resultSet.next()) {
                Program program = new Program();
                List<ICredit> creditList = new ArrayList<>();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

                program.setProgramNavn(resultSet.getString("program_navn"));
                program.setUdgivelsesDato(simpleDateFormat.parse(resultSet.getString("udgivelsesdato")));
                program.setProgramType(resultSet.getString("programtype"));
                program.setGenre(resultSet.getString("genre"));
                program.setProduktionsID(resultSet.getInt("id"));
                System.out.println(resultSet.getInt("id"));
                program.setLængde(resultSet.getDouble("laengde"));
                program.setCredits(creditList);//sets the list of credits to a default empty arraylist

                //if there's credits to be added, then adds the credits too
                IMapper iMapper = new CreditMapper();
                List<ICredit> iCreditList = (List<ICredit>) iMapper.getObject(program);
                if (iCreditList != null) {
                    program.setCredits(iCreditList);
                }
                programList.add(program);


            }

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return programList;
    }

    @Override
    public boolean updateObject(Object object) {//updates every column instead of updating a precise one.
        Program program = (Program) object;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("UPDATE program SET program_navn = ?, udgivelsesdato = ?, programtype = ?, genre = ?,  laengde = ?, program_image_path = ? WHERE id = ?");
            preparedStatement.setString(1, program.getProgramNavn());
            preparedStatement.setString(2, simpleDateFormat.format(program.getUdgivelsesDato()));
            preparedStatement.setString(3, program.getProgramType().toString());
            preparedStatement.setString(4, program.getGenre().toString());
            preparedStatement.setDouble(5, program.getLængde());
            preparedStatement.setString(6, program.getImagePath());

            //updates the creditlist
            IMapper iMapper = new CreditMapper();
            iMapper.putObject(program);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}


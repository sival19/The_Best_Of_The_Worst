package domain.objectMapper;

import domain.credits.Rolle;
import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;
import persistancy.database.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RolleMapper extends AbstractMapper {
    DatabaseConnector databaseConnector;
    PreparedStatement preparedStatement;

    public RolleMapper(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Object getObject(int oid) {
        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("SELECT rolletype FROM rolle WHERE rolle.id = OID");

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }

        String rolletype = preparedStatement.toString();

        Rolle rolle = new Rolle(rolletype, oid);

        return rolle;
    }

    @Override
    public void putObject(Object object) {
        Rolle rolle = (Rolle) object;

        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO rolle(rolletype) VALUES (?)");

            preparedStatement.setString(1, rolle.getRolletype());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();


        }


    }
}

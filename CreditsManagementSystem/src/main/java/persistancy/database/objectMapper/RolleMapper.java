package persistancy.database.objectMapper;

import domain.credits.Rolle;
import persistancy.database.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolleMapper implements IMapper {
    DatabaseConnector databaseConnector;
    PreparedStatement preparedStatement;

    public RolleMapper(){
        this.databaseConnector =  DatabaseConnector.getInstance() ;
    }

    @Override
    public Object getObject(Object oid) {
        Rolle rolle = null;
        try {
            preparedStatement = databaseConnector.getConnection().
                    prepareStatement("SELECT * FROM rolle WHERE rolle.id = ?");
            preparedStatement.setInt(1,(int)oid);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                rolle = new Rolle();
                rolle.setRolletype(resultSet.getString("rolletype"));
                rolle.setRolleID(resultSet.getInt("id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return rolle;
    }

    @Override
    public List<Object> getAllObjects() {
        List<Object> rolleList = new ArrayList<>();
        try {
            preparedStatement = databaseConnector.getConnection().
                    prepareStatement("SELECT * FROM rolle");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Rolle rolle = new Rolle();
                rolle.setRolletype(resultSet.getString("rolletype"));
                rolle.setRolleID(resultSet.getInt("id"));
                rolleList.add(rolle);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return rolleList;
    }

    @Override
    public boolean updateObject(Object object) {
        return false;
    }

    @Override
    public boolean putObject(Object object) {
        Rolle rolle = (Rolle) object;

        try {
            preparedStatement = databaseConnector.getConnection().prepareStatement("INSERT INTO rolle(rolletype) VALUES (?)");

            preparedStatement.setString(1, rolle.getRolletype());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return false;
        }
        return true;
    }
}

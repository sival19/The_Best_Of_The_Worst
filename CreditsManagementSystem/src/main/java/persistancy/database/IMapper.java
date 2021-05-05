package persistancy.database;

import domain.logIn.Bruger;
import org.postgresql.core.Oid;

import java.util.List;

public interface IMapper {

    Object getObject(Object oid);
    boolean putObject(Object object);
    List<Object> getAllObjects();

}

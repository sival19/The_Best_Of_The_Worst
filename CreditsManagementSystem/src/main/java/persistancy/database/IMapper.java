package persistancy.database;

import org.postgresql.core.Oid;

import java.util.List;

public interface IMapper {
    Object getObject(int oid);
    boolean putObject(Object object);
    List<Object> getAllObjects();
}

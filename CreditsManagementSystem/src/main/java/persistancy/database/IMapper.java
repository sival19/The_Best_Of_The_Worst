package persistancy.database;

import java.util.List;

public interface IMapper {

    Object getObject(Object oid);
    boolean putObject(Object object);
    List<Object> getAllObjects();

}

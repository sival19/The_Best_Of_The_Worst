package persistancy.database.objectMapper;

import java.util.List;

public interface IMapper {

    Object getObject(Object oid);
    boolean putObject(Object object);
    List<Object> getAllObjects();
    boolean updateObject(Object object);

}

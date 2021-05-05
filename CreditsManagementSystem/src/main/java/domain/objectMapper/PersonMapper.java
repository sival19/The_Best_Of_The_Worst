package domain.objectMapper;

import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;

import java.util.List;

public class PersonMapper extends AbstractMapper {
    @Override
    public Object getObject(Object oid) {
        return null;
    }

    @Override
    public List<Object> getAllObjects(){
        return null;
    }

    @Override
    public void putObject(Object object) {
        return false;
    }


    @Override
    public List<Object> getAllObjects() {
        return null;
    }
}

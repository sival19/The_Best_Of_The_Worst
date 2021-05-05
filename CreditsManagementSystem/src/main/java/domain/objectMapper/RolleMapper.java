package domain.objectMapper;

import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;

import java.util.List;

public class RolleMapper extends AbstractMapper {
    @Override
    public Object getObject(int oid) {
        return null;
    }

    @Override
    public boolean putObject(Object object) {
        return true;
    }

    @Override
    public List<Object> getAllObjects() {
        return null;
    }
}

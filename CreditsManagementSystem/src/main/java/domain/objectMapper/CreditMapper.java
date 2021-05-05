package domain.objectMapper;

import org.postgresql.core.Oid;
import persistancy.database.AbstractMapper;

import java.util.List;

public class CreditMapper extends AbstractMapper {
    @Override
    public Object getObject(int oid) {
        return null;
    }

    @Override
    public boolean putObject(Object object) {
        return false;
    }

    @Override
    public List<Object> getAllObjects() {
        return null;
    }
}

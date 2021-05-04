package persistancy.database;

import org.postgresql.core.Oid;

public interface IMapper {
    Object getObject(Oid oid);
    void putObject(Oid oid, Object object);
}

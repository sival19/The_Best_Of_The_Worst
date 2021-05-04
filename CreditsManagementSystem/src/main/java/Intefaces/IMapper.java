package Intefaces;

import org.postgresql.core.Oid;

public interface IMapper {
    Object getObject(int oid);
    void putObject(Object object);
}

package Intefaces;

import org.postgresql.core.Oid;

import java.util.List;

public interface IMapper {
    Object getObject(int oid);
    void putObject(Object object);
    List<Object> getAllObjects();
}

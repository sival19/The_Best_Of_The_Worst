package peristancy.file;

import peristancy.IDataBruger;

public interface IFileManager {
    IDataBruger loadBruger(String brugerNavn);
    boolean saveBruger(IDataBruger bruger);
}

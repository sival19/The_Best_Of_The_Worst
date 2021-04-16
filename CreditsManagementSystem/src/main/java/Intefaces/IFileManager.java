package Intefaces;

public interface IFileManager {
    IDataBruger loadBruger(String brugerNavn);
    boolean saveBruger(IDataBruger bruger);
}

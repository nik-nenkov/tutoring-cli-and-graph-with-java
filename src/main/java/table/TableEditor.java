package table;

public abstract class TableEditor {
    //TODO those should work ASAP

    abstract Table loadTableFromFile(String fileLocation);

    abstract void saveTableToFile(Table image, String fileLocation);

    abstract Table snapshotOfTableByValues(Table image);
}

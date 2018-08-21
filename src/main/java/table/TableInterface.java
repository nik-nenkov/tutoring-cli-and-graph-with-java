package table;

public interface TableInterface {
    void saveToFile(String fileLocation);

    Table loadFromFile(String fileLocation);

    void setCell(String cellPosition);

    Cell getCell(String cellPosition);
}

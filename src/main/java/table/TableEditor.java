package table;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TableEditor {
    //TODO those should work ASAP

    public static Table loadTableFromFileByExpressions(String fileLocation) throws FileNotFoundException {
        Table t = new Table();
        FileReader fr = new FileReader(fileLocation);

        return t;
    }

    public static void saveTableToFile(String location, Table image) throws IOException {
        PrintWriter pw = new PrintWriter("C://Users/Visitor/Desktop/table.txt");
        pw.write(image.toPrint());
        pw.close();
    }

    public static void saveTableToFileByExpressions(Table image) {

    }
}

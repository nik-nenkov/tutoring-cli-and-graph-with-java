package table;

import java.util.HashMap;

public class Table {

    private final HashMap<Integer, HashMap<Integer, Cell>> data;
    private int numRows;
    private int numCols;

    private Table(int r, int c) {
        data = new HashMap<>();
        numRows = r;
        numCols = c;
    }

    public Table() {
        this(0, 0);
    }

    synchronized void putCell(Cell c) {
        numRows = numRows < c.getRow() ? c.getRow() : numRows;
        numCols = numCols < c.getCol() ? c.getCol() : numCols;
        data.putIfAbsent(c.getRow(), new HashMap<>());
        data.get(c.getRow()).put(c.getCol(), c);
    }


    public String toPrint() {

        StringBuilder tableToPrint = new StringBuilder("\n   ||");

        for (int k = 1; k <= numCols; k++) {
            tableToPrint
                    .append("  ")
                    .append(toAlphabetical(k))
                    .append("  |");
        }

        tableToPrint
                .append("\n")
                .append(stringOfIdenticalSymbols(5, "="));

        for (int k = 1; k <= numCols; k++) {

            tableToPrint
                    .append(stringOfIdenticalSymbols(5, "="))
                    .append("|");
        }


        for (int i = 1; i <= numRows; i++) {
            tableToPrint
                    .append("\n ")
                    .append(i)
                    .append(" ||");

            for (int j = 1; j <= numCols; j++) {
                tableToPrint
                        .append("  ")
                        .append(getDataFromCell(i, j))
                        .append("  |");
            }

            tableToPrint
                    .append("\n")
                    .append(stringOfIdenticalSymbols(5, "-"));

            for (int j = 1; j <= numCols; j++) {
                tableToPrint
                        .append(stringOfIdenticalSymbols(5, "-"))
                        .append("-");
            }
        }

        return tableToPrint.toString();
    }

    private String getDataFromCell(int row, int col) {
        if (data.get(row) != null) {
            return data.get(row).get(col) == null
                    ? " "
                    : data.get(row).get(col).getContent().toString();
        }
        return " ";
    }

    /**
     * @param i is the decimal representation of a number
     * @return the same number but in a 26-base alphabetical representation
     */
    private String toAlphabetical(int i) {
        StringBuilder represent = new StringBuilder();
        while (i != 0) {
            represent.append(Character.toString((char) ((i % 26) + 64)));
            i /= 26;
        }
        return represent.toString();
    }

    private String stringOfIdenticalSymbols(int length, String symbol) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(symbol);
        }
        return sb.toString();
    }

    public Cell getCell(String position) {

        int row = toRow(position);
        int col = toCol(position);
        return this.data.get(row).get(col);
    }

    private int toRow(String position) {
        char[] alphabetical = position.split("([1-9]+)([0-9]*)$")[0].toCharArray();
        int val = 0;
        for (char letter : alphabetical) {
            val *= 26;
            val += ((int) letter - 64);
        }
        return val;
    }

    private int toCol(String position) {
        return Integer.parseInt(position.split("^([A-Z]+)")[1]);
    }
}

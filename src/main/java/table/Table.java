package table;

import expression.ExpressionParser;
import expression.ExpressionTree;

import java.util.HashMap;
import java.util.Map;

import static utilities.Converter.*;

public class Table {

    private final Map<Integer, Map<Integer, Cell>> data;
    private final Map<Integer, Integer> columnWidth;
    private final int DEFAULT_COLUMN_WIDTH = 5;
    private int numRows;
    private int numCols;

    private Table(int r, int c) {
        data = new HashMap<>();
        columnWidth = new HashMap<>();
        numRows = r;
        numCols = c;
    }

    public Table() {
        this(0, 0);
    }

    private static String stringOfIdenticalSymbols(int length, String symbol) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(symbol);
        }
        return sb.toString();
    }

    private int decideWidth(int column) {
        return columnWidth.get(column) == null ? DEFAULT_COLUMN_WIDTH : columnWidth.get(column);
    }

    void putCell(Cell c) {
        numRows = numRows < c.getRow() ? c.getRow() : numRows;
        numCols = numCols < c.getCol() ? c.getCol() : numCols;
        data.putIfAbsent(c.getRow(), new HashMap<>());
        data.get(c.getRow()).put(c.getCol(), c);

        fixWidth(c);
    }

    public String toPrint() {

        if (data.size() == 0) {
            return "\nEmpty table!\n";
        }

        StringBuilder tableToPrint = new StringBuilder("\n   ||");

        for (int k = 1; k <= numCols; k++) {
            tableToPrint
                    .append("  ")
                    .append(stringOfIdenticalSymbols(
                            decideWidth(k) - 4 - toAlphabetical(k).length(),
                            " "
                    ))
                    .append(toAlphabetical(k))
                    .append("  |");
        }

        tableToPrint
                .append("\n")
                .append(stringOfIdenticalSymbols(DEFAULT_COLUMN_WIDTH, "="));

        for (int k = 1; k <= numCols; k++) {

            tableToPrint
                    .append(stringOfIdenticalSymbols(decideWidth(k), "="))
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
                        .append(stringOfIdenticalSymbols(
                                decideWidth(j) - 4 - getDataFromCell(i, j).length(),
                                " "
                        ))
                        .append(getDataFromCell(i, j))
                        .append("  |");
            }

            tableToPrint
                    .append("\n")
                    .append(stringOfIdenticalSymbols(DEFAULT_COLUMN_WIDTH, "-"));

            for (int k = 1; k <= numCols; k++) {
                tableToPrint
                        .append(stringOfIdenticalSymbols(decideWidth(k), "-"))
                        .append("-");
            }
        }

        return tableToPrint.toString();
    }

    private String getDataFromCell(int row, int col) {
        if (data.get(row) != null) {
            return data.get(row).get(col) == null
                    ? " "
                    : data.get(row).get(col).toString();
        }
        return " ";
    }

    private int getColumnWidth(int column) {
        return columnWidth.get(column) == null ? DEFAULT_COLUMN_WIDTH : columnWidth.get(column);
    }

    public Cell getCell(String position) {
        int row = toRow(position);
        int col = toCol(position);
        return this.data.get(row).get(col);
    }

    /**
     * Checks if cell exists on
     *
     * @param cellPosition if so - change that cell, setting
     * @param parsedTree   to that cell,
     *                     if not - create new cell and put it in the map
     */
    private void setCell(String cellPosition, ExpressionTree parsedTree) {
        if (data.get(toRow(cellPosition)) != null
                && data.get(toRow(cellPosition)).get(toCol(cellPosition)) != null) {
            Cell c = getCell(cellPosition);
            c.setExpression(parsedTree);
            fixWidth(c);
        } else {
            Cell c = new Cell(cellPosition, parsedTree);
            this.putCell(c);
        }
    }

    public void setCellByValue(String cellPosition, Double value) {
        if (data.get(toRow(cellPosition)) != null
                && data.get(toRow(cellPosition)).get(toCol(cellPosition)) != null) {
            Cell c = getCell(cellPosition);
            c.setValue(value);
            fixWidth(c);
        } else {
            Cell c = new Cell(cellPosition, value);
            this.putCell(c);
        }
    }

    private void fixWidth(Cell c) {
        columnWidth.put(
                c.getCol(),
                c.toString().length() + 4 > getColumnWidth(c.getCol())
                        ? c.toString().length() + 4
                        : getColumnWidth(c.getCol()
                )
        );
    }

    public void setCellByExpression(String cellPosition, String expressionTobeParsed) {
        ExpressionParser ep = new ExpressionParser(this);
        setCell(cellPosition, ep.parse(expressionTobeParsed));
    }

    String displayAllExpressions() {
        StringBuilder sb = new StringBuilder();
        this.data.forEach((row, v) -> v.forEach((col, cell) -> {
            sb.append("\n\t")
                    .append(toCellReference(row, col))
                    .append("=")
                    .append(cell.getExpressionAsString());
        }));
        return sb.toString();
    }
}

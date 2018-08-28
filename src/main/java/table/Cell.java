package table;

import expression.ExpressionTree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private static final String ERROR_STATE = "Error";
    private static final String INITIALIZED_STATE = "Initialized";
    private static final String NOT_INITIALIZED_STATE = "Not initialized";

    private final String position;
    private String content;
    private int row;
    private int col;
    private BigDecimal value;
    private ExpressionTree expression;

    /**
     * Support multiple dependencies for each node:
     */
    private List<Cell> dependencies;

    /**
     * Support multiple observers on each cell:
     */
    private List<Cell> observers = new ArrayList<>();

    /**
     * Handling of cell's states:
     */
    private boolean initialized = false;
    private boolean hasError = false;
    private String state = NOT_INITIALIZED_STATE;

    /**
     * Constructor:
     */
    public Cell(String position, String content) {
        this.position = position;
        this.content = content;
        this.row = parseRow(position);
        this.col = parseCol(position);
    }

    Cell(String position, long value) {
        this.position = position;
        this.value = BigDecimal.valueOf(value);
        this.row = parseRow(position);
        this.col = parseCol(position);
    }

    Cell(String position, double value) {
        this.position = position;
        this.value = BigDecimal.valueOf(value);
        this.row = parseRow(position);
        this.col = parseCol(position);
    }

    Cell(String position, ExpressionTree value) {
        this.position = position;
        this.value = value.getValue();
        this.row = parseRow(position);
        this.col = parseCol(position);
        this.expression = value;
    }

    public static boolean isCellRefference(String input) {
        return input.matches("");
    }

    /**
     * Recalculating only whats relevant(not everything):
     */
    private void recalculate() {
        boolean response = notifyObservers();
    }

    private int parseRow(String position) {
        return Integer.parseInt(position.split("^([A-Z]+)")[1]);
    }

    private int parseCol(String position) {
        String alphabeticalColumn = position.split("([1-9]+)([0-9]*)$")[0];
        int number = 0;
        for (int i = alphabeticalColumn.length() - 1; i >= 0; i--) {
            number = number * 26;
            number = number + ((int) alphabeticalColumn.charAt(i) - 64);
        }
        return number;
    }

    /**
     * Getters and setters:
     */

    private boolean notifyObservers() {
        observers.forEach(Cell::notify);
        return false;
    }

    @Override
    public String toString() {
        if (value == null) {
            if (content == null) {
                return expression.getValue().stripTrailingZeros().toPlainString();
            }
            return content;
        }
        return value.stripTrailingZeros().toPlainString();
    }

    public ExpressionTree getExpression() {
        return expression;
    }

    public void setExpression(ExpressionTree expression) {
        this.expression = expression;
    }

    int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getPosition() {
        return position;
    }
}

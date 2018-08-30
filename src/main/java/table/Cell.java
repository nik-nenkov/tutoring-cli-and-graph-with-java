package table;

import expression.ExpressionTree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static utilities.Converter.toCol;
import static utilities.Converter.toRow;

public class Cell {
    private static final String ERROR_STATE = "Error";
    private static final String INITIALIZED_STATE = "Initialized";
    private static final String NOT_INITIALIZED_STATE = "Not initialized";

    private final String position;
    private String content;
    private int row;
    private int col;
    private BigDecimal value;
    private ExpressionTree expressionTree;
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
        this.row = toRow(position);
        this.col = toCol(position);
    }

    Cell(String position, long value) {
        this.position = position;
        this.value = BigDecimal.valueOf(value);
        this.row = toRow(position);
        this.col = toCol(position);
    }

    Cell(String position, double value) {
        this.position = position;
        this.value = BigDecimal.valueOf(value);
        this.row = toRow(position);
        this.col = toCol(position);
    }

    Cell(String position, ExpressionTree value) {
        this.position = position;
        this.value = value.getValue();
        this.row = toRow(position);
        this.col = toCol(position);
        this.expressionTree = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    void setValue(Double value) {
        this.value = BigDecimal.valueOf(value);
        this.expressionTree = null;
        this.content = null;
    }

    /**
     * Recalculating only whats relevant(not everything):
     */
    private void recalculate() {
        boolean response = notifyObservers();
    }

    private boolean notifyObservers() {
        observers.forEach(Cell::notify);
        return false;
    }

    @Override
    public String toString() {
        if (expressionTree != null)
            return expressionTree
                    .getResult()
                    .setScale(6, RoundingMode.HALF_UP)
                    .stripTrailingZeros()
                    .toPlainString();

        if (value != null)
            return value
                    .setScale(6, RoundingMode.HALF_UP)
                    .stripTrailingZeros()
                    .toPlainString();

        if (content != null) return content;

        return " ";
    }

    /**
     * Getters and setters:
     */

    String getExpressionAsString() {
        if (expressionTree != null) return expressionTree.getExpression();
        if (value != null) return value.setScale(3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        else return null;
    }

    public void setExpression(ExpressionTree expression) {
        this.expressionTree = expression;
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

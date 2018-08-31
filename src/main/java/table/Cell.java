package table;

import expression.ExpressionTree;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static utilities.Converter.toCol;
import static utilities.Converter.toRow;

public class Cell {
    private static final String ERROR_STATE = "Error";
    private static final String INITIALIZED_STATE = "Initialized";
    private static final String NOT_INITIALIZED_STATE = "Not initialized";

    private String content;
    private int row;
    private int col;
    private BigDecimal value;
    private ExpressionTree expressionTree;

    private Cell(String position, String content, BigDecimal value, ExpressionTree et) {
        this.row = toRow(position);
        this.col = toCol(position);
        this.content = content;
        this.value = value;
        this.expressionTree = et;
    }

    public Cell(String position, String content) {
        this(position, content, null, null);
    }

    Cell(String position, BigDecimal value) {
        this(position, null, value, null);
    }

    Cell(String position, ExpressionTree expressionTree) {
        this(position, null, null, expressionTree);
    }

    public ExpressionTree getExpressionTree() {
        return expressionTree;
    }

    public BigDecimal getValue() {
        return this.expressionTree == null ? this.value : this.expressionTree.getValue();
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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

        else return content;

    }

    /**
     * Getters and setters:
     */

    String getExpressionAsString() {
        if (expressionTree != null) {
            return expressionTree.getExpression();
        } else {
            return value.setScale(3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        }
    }

    public void setExpression(ExpressionTree expression) {
        this.expressionTree = expression;
    }

    int getCol() {
        return col;
    }

    int getRow() {
        return row;
    }
}

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

    private final String position;
    private String content;
    private int row;
    private int col;
    private BigDecimal value;
    private ExpressionTree expressionTree;

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

    Cell(String position, ExpressionTree et) {
        this.position = position;
        this.value = et.getValue();
        this.row = toRow(position);
        this.col = toCol(position);
        this.expressionTree = et;
    }

    public ExpressionTree getExpressionTree() {
        return expressionTree;
    }

    void setValue(Double value) {
        this.value = BigDecimal.valueOf(value);
    }

    public BigDecimal getValue() {
        return this.expressionTree == null ? this.value : this.expressionTree.getValue();
    }
    @Override
    public String toString() {
        //TODO make this return information if cell is still not calculated by graph !!!

        try {
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
        } catch (Exception e) {
            return " ";
        }
        return " ";
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

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

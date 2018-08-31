package expression;

import table.Table;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Double.parseDouble;
import static java.math.RoundingMode.HALF_UP;
import static utilities.Converter.replaceMultipleSignOperators;
import static utilities.Validator.isValidCellPosition;

public class LeafNode implements Node {
    private static Table referenceTable;

    public static void setReferenceTable(Table referenceTable) {
        LeafNode.referenceTable = referenceTable;
    }

    private final boolean isReference;
    private final BigDecimal value;
    private final String cellReference;

    LeafNode(String initial) {
        if (isValidCellPosition(initial)) {
            this.isReference = true;
            this.cellReference = initial;
            this.value = null;
        } else {
            this.isReference = false;
            this.cellReference = null;
            this.value = new BigDecimal(parseDouble(replaceMultipleSignOperators(initial)), new MathContext(15, HALF_UP));
        }
    }

    @Override
    public BigDecimal getValue() {
        if (isReference) {
            return referenceTable == null ? null : referenceTable.getCell(cellReference).getValue();
        }
        return value;
    }

    @Override
    public String getExpression() {
        if (isReference) {
            return cellReference;
        }
        return value.setScale(3, HALF_UP).stripTrailingZeros().toPlainString();
    }

    @Override
    public boolean isReference() {
        return isReference;
    }

    @Override
    public String getReference() {
        return cellReference;
    }

    @Override
    public String toString() {
        return value == null ? cellReference : value.setScale(2, HALF_UP).stripTrailingZeros().toPlainString();
    }
}

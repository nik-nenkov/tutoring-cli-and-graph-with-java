package expression;

import table.Cell;
import table.Table;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Double.parseDouble;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static utilities.Validator.isValidCellPosition;

class LeafNode implements Node {
    private final boolean isReference;
    private final BigDecimal value;
    private final Cell cellReference;

    LeafNode(String initial, Table tableToRefer) {
        if (isValidCellPosition(initial)) {
            this.isReference = true;
            this.cellReference = tableToRefer.getCell(initial);
            this.value = cellReference.getValue();
        } else {
            this.isReference = false;
            this.cellReference = null;
            this.value = new BigDecimal(parseDouble(initial), new MathContext(15, HALF_UP));
        }
    }

    @Override
    public BigDecimal getValue() {
        if (isReference) {
            return cellReference.getValue();
        }
        return value == null ? ZERO : value;
    }

    @Override
    public String getExpression() {
        if (isReference) {
            return cellReference.getPosition();
        }
        return value.setScale(3, HALF_UP).stripTrailingZeros().toPlainString();
    }

//    public Cell getCellReference() {
//        return cellReference;
//    }
//
//    public boolean isReference() {
//        return isReference;
//    }

}

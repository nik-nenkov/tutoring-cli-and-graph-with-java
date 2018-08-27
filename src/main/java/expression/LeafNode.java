package expression;

import table.Cell;

import java.math.BigDecimal;
import java.math.RoundingMode;

class LeafNode implements Node {
    private BigDecimal value;
    private Cell cellReference;

    LeafNode(String initial) {
        value = BigDecimal.valueOf(Double.parseDouble(initial)).setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getValue() {
        return value == null ? BigDecimal.ZERO : value.setScale(6, RoundingMode.HALF_UP);
    }

}

package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
//import java.math.MathContext;
//import java.math.RoundingMode;

class LeafNode extends Node {
    private BigDecimal value;

    LeafNode(String initial) {
        value = BigDecimal.valueOf(Double.parseDouble(initial));
    }

    @Override
    BigDecimal getValue() {
        return value == null ? BigDecimal.ZERO : value;
    }

    @Override
    public String toString() {
        return value.setScale(0, RoundingMode.HALF_UP).toString();
    }
}

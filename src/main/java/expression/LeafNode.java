package expression;

import java.math.BigDecimal;

class LeafNode extends Node {
    private BigDecimal value;

    LeafNode(String initial) {
        value = BigDecimal.valueOf(Double.parseDouble(initial));
    }

    @Override
    BigDecimal getValue() {
        return value;
    }
}

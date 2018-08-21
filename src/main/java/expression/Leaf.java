package expression;

import java.math.BigDecimal;

class Leaf extends Node {
    private BigDecimal value;

    Leaf(String initial) {
        value = BigDecimal.valueOf(Double.parseDouble(initial));
    }

    @Override
    BigDecimal getValue() {
        return value;
    }
}

package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Node {
    private BigDecimal value;

    Node(String initial) {
        value = BigDecimal.valueOf(Double.parseDouble(initial)).setScale(6, RoundingMode.HALF_UP);
    }

    public Node() {
        value = BigDecimal.ZERO;
    }

    BigDecimal getValue() {
        return value == null ? BigDecimal.ZERO : value.setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

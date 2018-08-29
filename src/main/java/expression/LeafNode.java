package expression;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Double.parseDouble;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

class LeafNode implements Node {
    private BigDecimal value;

//    TODO make it possible to have Cell instead of number
//    private Cell cellReference;

    LeafNode(String initial) {
        value = new BigDecimal(parseDouble(initial), new MathContext(15, HALF_UP));
    }

    @Override
    public BigDecimal getValue() {
        return value == null ? ZERO : value;
    }

    @Override
    public String getExpression() {
        return value.setScale(3, HALF_UP).stripTrailingZeros().toPlainString();
    }

}

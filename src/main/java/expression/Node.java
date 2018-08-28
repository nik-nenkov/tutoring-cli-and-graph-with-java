package expression;

import java.math.BigDecimal;

interface Node {
    BigDecimal getValue();
    String getExpression();
}

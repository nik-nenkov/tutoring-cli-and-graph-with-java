package expression;

import java.math.BigDecimal;

public interface Node {
    BigDecimal getValue();

    String getExpression();

    boolean isReference();

    String getReference();

}

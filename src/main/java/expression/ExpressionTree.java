package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;

class ExpressionTree implements Node {
    private Node root;
    ExpressionTree(Node n) {
        root = n;
    }

    @Override
    public BigDecimal getValue() {
        return root == null ? BigDecimal.ZERO
                : root.getValue().setScale(6, RoundingMode.HALF_UP);
    }

}

package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class ExpressionTree implements Node {

    private final Node root;
    private final Set<Node> nodes;

    ExpressionTree(Node root, Set<Node> nodes) {
        this.root = root;
        this.nodes = nodes;
    }

    @Override
    public BigDecimal getValue() {
        return root == null ? BigDecimal.ZERO
                : root.getValue().setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public String getExpression() {
        return root.getExpression();
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Node getRoot() {
        return root;
    }
}

package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

class ExpressionTree extends Node {
    private Node root;
    private HashSet<InnerNode> innerNodes;
    private HashSet<Node> nodes;

    ExpressionTree(Node n) {
        super();
        root = n;
    }

    @Override
    public BigDecimal getValue() {
        return root == null ? BigDecimal.ZERO
                : root.getValue().setScale(6, RoundingMode.HALF_UP);
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public HashSet<Node> getNodes() {
        return nodes;
    }

    public void setNodes(HashSet<Node> nodes) {
        this.nodes = nodes;
    }

    public HashSet<InnerNode> getInnerNodes() {
        return innerNodes;
    }

    public void setInnerNodes(HashSet<InnerNode> innerNodes) {
        this.innerNodes = innerNodes;
    }
}

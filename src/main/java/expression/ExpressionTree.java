package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

class ExpressionTree extends Node {
    private Node root;
    private HashSet<InnerNode> innerNodes;
    private HashSet<LeafNode> leafNodes;


    ExpressionTree(Node n) {
        root = n;
    }

    @Override
    BigDecimal getValue() {
        return root == null ? BigDecimal.ZERO
                : root.getValue().setScale(3, RoundingMode.HALF_UP);
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public HashSet<LeafNode> getLeafNodes() {
        return leafNodes;
    }

    public void setLeafNodes(HashSet<LeafNode> leafNodes) {
        this.leafNodes = leafNodes;
    }

    public HashSet<InnerNode> getInnerNodes() {
        return innerNodes;
    }

    public void setInnerNodes(HashSet<InnerNode> innerNodes) {
        this.innerNodes = innerNodes;
    }
}

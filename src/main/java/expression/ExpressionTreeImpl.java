package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

class ExpressionTreeImpl extends Node implements ExpressionTree {
    private Node root;
    private HashSet<InnerNode> innerNodes;
    private HashSet<LeafNode> leafNodeHashSet;

    ExpressionTreeImpl(Node n) {
        root = n;
    }

    @Override
    BigDecimal getValue() {

        return root.getValue() == null ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : root.getValue().setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void setNewRootMoveOldLeft(Node newRoot) {

    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public HashSet<LeafNode> getLeafNodeHashSet() {
        return leafNodeHashSet;
    }

    public void setLeafNodeHashSet(HashSet<LeafNode> leafNodeHashSet) {
        this.leafNodeHashSet = leafNodeHashSet;
    }

    public HashSet<InnerNode> getInnerNodes() {
        return innerNodes;
    }

    public void setInnerNodes(HashSet<InnerNode> innerNodes) {
        this.innerNodes = innerNodes;
    }
}

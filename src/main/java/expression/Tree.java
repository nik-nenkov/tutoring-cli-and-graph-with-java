package expression;

import java.math.BigDecimal;
import java.util.HashSet;

class Tree extends Node {
    private Node root;
    private HashSet<InnerNode> innerNodes;
    private HashSet<Leaf> leafNodeHashSet;

    Tree() {

    }

    @Override
    BigDecimal getValue() {
        return root.getValue();
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public HashSet<Leaf> getLeafNodeHashSet() {
        return leafNodeHashSet;
    }

    public void setLeafNodeHashSet(HashSet<Leaf> leafNodeHashSet) {
        this.leafNodeHashSet = leafNodeHashSet;
    }

    public HashSet<InnerNode> getInnerNodes() {
        return innerNodes;
    }

    public void setInnerNodes(HashSet<InnerNode> innerNodes) {
        this.innerNodes = innerNodes;
    }
}

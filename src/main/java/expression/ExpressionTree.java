package expression;

import table.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;


/**
 * Notice that getValue() returns BigDecimal value with maximum accuracy
 * If you wish to print a result - please use the getResult() method
 */
public class ExpressionTree implements Node {

    private static Table referenceTable;
    private final String reference;
    private final Node root;
    private final Set<Node> nodes;

    ExpressionTree(Node root, Set<Node> nodes) {
        this.root = root;
        this.nodes = nodes;
        this.reference = null;
    }

    ExpressionTree(Node root, Set<Node> nodes, String reference) {
        this.root = root;
        this.nodes = nodes;
        this.reference = reference;
    }

    public static void setReferenceTable(Table rt) {
        referenceTable = rt;
    }

    @Override
    public BigDecimal getValue() {
        return root.getValue();
    }

    @Override
    public String getExpression() {
        return root.getExpression();
    }

    @Override
    public boolean isReference() {
        return reference != null;
    }

    @Override
    public String getReference() {
        return this.reference;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public BigDecimal getResult() {
        BigDecimal result = root.getValue();

        if (result.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
            return result.setScale(0, RoundingMode.HALF_UP);
        }
        return root.getValue().setScale(9, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    Node getRoot() {
        return this.root;
    }
}

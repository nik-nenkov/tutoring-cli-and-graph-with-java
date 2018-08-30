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

    private final Node root;
    private final Set<Node> nodes;
    private final Table referenceTable;

    ExpressionTree(Node root, Set<Node> nodes, Table referenceTable) {
        this.root = root;
        this.nodes = nodes;
        this.referenceTable = referenceTable;
    }

    @Override
    public BigDecimal getValue() {
        return root == null ? BigDecimal.ZERO
                : root.getValue();
    }

    @Override
    public String getExpression() {
        return root.getExpression();
    }

    Set<Node> getNodes() {
        return nodes;
    }

    public BigDecimal getResult() {
        BigDecimal result = root.getValue();

        if (result.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
            return result.setScale(0, RoundingMode.HALF_UP);
        }
        return root.getValue().setScale(9, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public Table getReferenceTable() {
        return referenceTable;
    }
}

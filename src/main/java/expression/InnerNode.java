package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;

class InnerNode implements Node {
    private Node leftNode;
    private Node rightNode;
    private Operation operation;

    InnerNode(String op) {
        operation = Operation.fromString(op);
    }

    @Override
    public BigDecimal getValue() {
        BigDecimal a = leftNode == null ? BigDecimal.ZERO : leftNode.getValue();
        BigDecimal b = rightNode == null ? BigDecimal.ZERO : rightNode.getValue();
        return operation.calculate(a, b).setScale(6, RoundingMode.HALF_UP);
    }

    void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    int getPriority() {
        return operation.getPriority();
    }
}

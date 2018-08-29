package expression;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

class InnerNode implements Node {
    private Node leftNode;
    private Node rightNode;
    private Operation operation;

    InnerNode(String op) {
        operation = Operation.fromString(op);
    }

    @Override
    public BigDecimal getValue() {
        BigDecimal a = leftNode == null ? ZERO : leftNode.getValue();
        BigDecimal b = rightNode == null ? ZERO : rightNode.getValue();
        return operation.calculate(a, b).setScale(6, HALF_UP);
    }

    @Override
    public String getExpression() {
        StringBuilder sb = new StringBuilder();

        if (leftNode.getClass().equals(ExpressionTree.class)) {
            sb.append("(").append(leftNode.getExpression()).append(")");
        } else {
            sb.append(leftNode.getExpression());
        }

        sb.append(operation.getOperator());

        if (rightNode.getClass().equals(ExpressionTree.class)) {
            sb.append("(").append(rightNode.getExpression()).append(")");
        } else {
            sb.append(rightNode.getExpression());
        }

        return sb.toString();
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

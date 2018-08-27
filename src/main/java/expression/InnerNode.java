package expression;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_UP;

class InnerNode extends Node {
    private Node leftNode;
    private Node rightNode;
    private Operation operation;

    InnerNode(String op) {
        operation = Operation.fromString(op);
    }

    @Override
    public BigDecimal getValue() {
        BigDecimal a = leftNode == null ? BigDecimal.ZERO : leftNode.getValue().setScale(6, RoundingMode.HALF_UP);
        BigDecimal b = rightNode == null ? BigDecimal.ZERO : rightNode.getValue().setScale(6, RoundingMode.HALF_UP);
        return calculate(a, operation, b).setScale(6, RoundingMode.HALF_UP);
    }

    private BigDecimal calculate(BigDecimal a, Operation operation, BigDecimal b) {
        switch (operation) {
            case Division:
                return a.divide(b, HALF_UP).setScale(6, RoundingMode.HALF_UP);
            case Subtraction:
                return a.subtract(b).setScale(6, RoundingMode.HALF_UP);
            case Addition:
                return a.add(b).setScale(6, RoundingMode.HALF_UP);
            case Exponentiation:
                return BigDecimal.valueOf(Math.pow(a.doubleValue(), b.doubleValue())).setScale(6, RoundingMode.HALF_UP);
            case Multiplication:
                return a.multiply(b).setScale(6, RoundingMode.HALF_UP);
            default:
                return BigDecimal.ZERO;
        }
    }

//    public Node getRightNode() {
//        return rightNode;
//    }

    void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

//    public Node getLeftNode() {
//        return leftNode;
//    }

    void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    @Override
    public String toString() {
        if (leftNode == null && rightNode == null) {
            return operation.getOperator();
        } else return getValue().toString();
    }

    int getPriority() {
        return operation.getPriority();
    }
}

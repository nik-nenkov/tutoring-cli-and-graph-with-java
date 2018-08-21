package expression;

import java.math.BigDecimal;

class InnerNode extends Node {
    private Node leftNode;
    private Node rightNode;
    private Operation operation;

    InnerNode(String op) {
        operation = Operation.fromString(op);
    }

    @Override
    BigDecimal getValue() {
        BigDecimal a = leftNode == null ? BigDecimal.ZERO : leftNode.getValue();
        BigDecimal b = rightNode == null ? BigDecimal.ZERO : rightNode.getValue();
        return calculate(a,operation,b);
    }

    private BigDecimal calculate(BigDecimal a, Operation operation, BigDecimal b) {
        switch (operation){
            case Division:
                return a.divide(b);
            case Subtraction:
                return a.subtract(b);
            case Addition:
                return a.add(b);
            case Exponentiation:
                return BigDecimal.valueOf(Math.pow(a.doubleValue(), b.doubleValue()));
            case Multiplication:
                return a.multiply(b);
            default:
                return BigDecimal.ZERO;
        }
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }
}

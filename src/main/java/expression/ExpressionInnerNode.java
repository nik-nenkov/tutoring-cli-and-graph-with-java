package expression;

public class ExpressionInnerNode extends ExpressionNode {
    private ExpressionNode leftNode;
    private ExpressionNode rightNode;
    private Operation operation;

    @Override
    Integer getValue() {
        Integer a = leftNode==null?0:leftNode.getValue();
        Integer b = rightNode==null?0:rightNode.getValue();
        return calculate(a,operation,b);
    }

    private Integer calculate(Integer a, Operation operation, Integer b) {
        switch (operation){
            case Division:
                return a/b;
            case Subtraction:
                return a-b;
            case Addition:
                return a+b;
            case Exponentiation:
                return (int)Math.pow(a,b);
            case Multiplication:
                return a*b;
            default:
                return 0;
        }
    }
}

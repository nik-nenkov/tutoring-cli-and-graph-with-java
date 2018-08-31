package expression;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

public enum Operation {
    Division("/"),
    Multiplication("*"),
    Addition("+"),
    Subtraction("-"),
    Exponentiation("^");

    private final Integer priority;
    private final String operator;

    private final MathContext mc = new MathContext(80, RoundingMode.FLOOR);

    Operation(String s) {
        operator = s;
        switch (s) {
            case "+":
                priority = 0;
                break;
            case "-":
                priority = 1;
                break;
            case "*":
                priority = 2;
                break;
            case "/":
                priority = 3;
                break;
            default:
                priority = 4;
        }
    }

    public static Operation fromString(String s) throws InvalidParameterException {
        switch (s) {
            case "+":
                return Operation.Addition;
            case "-":
                return Operation.Subtraction;
            case "*":
                return Operation.Multiplication;
            case "/":
                return Operation.Division;
            case "^":
                return Operation.Exponentiation;
            default:
                throw new RuntimeException() {
                    @Override
                    public String getMessage() {
                        return "invalid constructor operation: \"" + s + "\"";
                    }
                };
        }
    }

    public Integer getPriority() {
        return priority;
    }

    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        switch (this) {
            case Division:
                return a.divide(b, mc);
            case Subtraction:
                return a.subtract(b);
            case Addition:
                return a.add(b);
            case Multiplication:
                return a.multiply(b);
            default:
                return BigDecimalMath.pow(a, b, mc);
        }
    }

    public String getOperator() {
        return operator;
    }

}

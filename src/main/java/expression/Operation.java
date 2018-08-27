package expression;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import static java.math.RoundingMode.HALF_UP;

public enum Operation {
    Division("/"),
    Multiplication("*"),
    Addition("+"),
    Subtraction("-"),
    Exponentiation("^");

    private final Integer priority;

    Operation(String s) {
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
            default:
                return Operation.Exponentiation;
        }
    }

    public Integer getPriority() {
        return priority;
    }

    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        switch (this) {
            case Division:
                return a.divide(b, HALF_UP);
            case Subtraction:
                return a.subtract(b);
            case Addition:
                return a.add(b);
            case Multiplication:
                return a.multiply(b);
            default:
                return BigDecimal.valueOf(Math.pow(a.doubleValue(), b.doubleValue()));
        }
    }
}

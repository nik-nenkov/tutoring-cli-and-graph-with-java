package expression;

import java.security.InvalidParameterException;

public enum Operation {
    Division("/"),
    Multiplication("*"),
    Addition("+"),
    Subtraction("-"),
    Exponentiation("^");

    final private String operator;
    final private Integer priority;

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
            case "^":
                priority = 4;
                break;
            default:
                throw new InvalidParameterException();
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
                throw new InvalidParameterException();
        }
    }

    public String getOperator() {
        return operator;
    }

    public Integer getPriority() {
        return priority;
    }
}

package utilities;

public final class Validator {

    private static final String SINGLE_BRACKETS_CLOSED = "^[(]([a-zA-Z0-9])+[)]$";

    public static final String VALID_NUMBER_PATTERN = "([1-9]+[0-9]*\\.?[0-9]*)";

    public static final String VALID_OPERATOR_PATTERN = "([+\\-*/^])";

    private static final String CELL_POSITION_PATTERN = "^([A-Z]{1,6})([1-9])([0-9]{0,9})$";

    private static final String DECIMAL_PATTERN = "(([1-9]+[0-9]*)?(\\.[0-9]+)?)";

    private static final String OPERATION_PATTERN = "([+\\-/*^])";

    private static final String EXPRESSION_PATTERN = "^(((([A-Z]{1,6})([1-9])([0-9]{0,9}))|(([1-9]+[0-9]*)?(\\.[0-9]+)?))([\\s]*)([+\\-/*^])?)*$";


    public static boolean isValidCellPosition(String position) {
        return position.matches(CELL_POSITION_PATTERN);
    }

    static boolean isValidExpression(String expression) {
        return expression.matches(EXPRESSION_PATTERN);
    }

    private static boolean isSingleBracketsClosed(String input) {
        return input.matches(SINGLE_BRACKETS_CLOSED);
    }

    static boolean isValidDecimal(String candidate) {
        return candidate.matches(DECIMAL_PATTERN);
    }

    static boolean isValidOperation(String candidate) {
        return candidate.matches(OPERATION_PATTERN);
    }

    public static void main(String[] args) {
        System.out.println(isSingleBracketsClosed("(sadh99343)"));
        System.out.println(isSingleBracketsClosed("(sa)dh99(343)"));
    }
}

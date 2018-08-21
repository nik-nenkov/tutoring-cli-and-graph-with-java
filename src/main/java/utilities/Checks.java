package utilities;

public final class Checks {

    private static final String CELL_POSITION_PATTERN = "^([A-Z]{1,6})([1-9])([0-9]{0,9})$";
    public static final String DECIMAL_PATTERN = "(([1-9]+[0-9]*)?(\\.[0-9]+)?)";
    public static final String OPERATION_PATTERN = "([+\\-/*^])";
    private static final String EXPRESSION_PATTERN = "" +
            "^(((([A-Z]{1,6})([1-9])([0-9]{0,9}))|(([1-9]+[0-9]*)?(\\.[0-9]+)?))([\\s]*)([+\\-/*^])?)*$";

    public static boolean isValidCellPosition(String position) {
        return position.matches(CELL_POSITION_PATTERN);
    }

    public static boolean isValidExpression(String expression) {
        return expression.matches(EXPRESSION_PATTERN);
    }

}

package expression;

final class Validator {
    private static final String VALID_OPERATOR_PATTERN = "([+\\-*/^])";
    private static final String VALID_DECIMAL_PATTERN = "(([1-9][0-9]*)|([0]))([.][0-9]{1,8})?";

    static String removeEmptySpaces(String input) {
        return input.trim()
                .replace("\n", "")
                .replace("\t", "")
                .replace(" ", "");
    }

    static boolean isValidOperator(String input) {
        return input.matches("^" + VALID_OPERATOR_PATTERN + "$");
    }

    static boolean isValidDecimal(String input) {
        return input.matches("^" + VALID_DECIMAL_PATTERN + "$");
    }

    static String[] extractDecimals(String input) {
        return input.split(VALID_DECIMAL_PATTERN);
    }

    static String[] extractOperators(String input) {
        return input.split(VALID_OPERATOR_PATTERN);
    }
}

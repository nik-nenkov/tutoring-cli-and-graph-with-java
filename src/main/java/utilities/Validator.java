package utilities;

import java.security.InvalidParameterException;

public final class Validator {
    private static final String VALID_OPERATOR_PATTERN = "([+\\-*/^])";
    private static final String VALID_DECIMAL_PATTERN = "(([1-9][0-9]*)|([0]))([.][0-9]{1,8})?";
    private static final String CELL_POSITION_PATTERN = "([A-Z]{1,6})([1-9])([0-9]{0,9})";
    private static final String VALID_OPERAND_PATTERN = "(" + VALID_DECIMAL_PATTERN + ")|(" + CELL_POSITION_PATTERN + ")";

    public static String removeEmptySpaces(String input) {
        return input.trim()
                .replace("\n", "")
                .replace("\t", "")
                .replace(" ", "");
    }

    public static String[] extractOperands(String input) {
        return input.split(VALID_OPERATOR_PATTERN);
    }

    @Deprecated
    public static String[] extractDecimals(String input) {
        return input.split(VALID_DECIMAL_PATTERN);
    }

    public static String[] extractOperators(String input) {
        return input.split(VALID_OPERAND_PATTERN);
    }


    private static final String EXPRESSION_PATTERN =
            "^(((([A-Z]{1,6})([1-9])([0-9]{0,9}))|" +
                    "(([1-9]+[0-9]*)?(\\.[0-9]+)?))([\\s]*)([+\\-/*^])?)*$";


    public static boolean isValidCellPosition(String position) {
        return position.matches(CELL_POSITION_PATTERN);
    }

    static boolean isValidExpression(String expression) {
        return expression.matches(EXPRESSION_PATTERN);
    }

    static String extractTheThingFromInsideTheBrackets(String input) {
        if (hasValidOrderOfBrackets(input)) {
            return removePart(input);
        } else throw new InvalidParameterException();
    }

    private static int checkIfCharacterIsBracket(char character) {
        switch (character) {
            case '(':
                return 1;
            case ')':
                return -1;
            default:
                return 0;
        }
    }

    private static String removePart(String input) {

        int start = 0;
        int end = input.length();

        for (int i = 0; i < input.length() - 2; i++) {
            if (checkIfCharacterIsBracket(input.charAt(i)) == 1) {
                start = i + 1;
                break;
            }
        }

        for (int i = input.length() - 1; i >= 0; i--) {
            if (checkIfCharacterIsBracket(input.charAt(i)) == -1) {
                end = i;
                break;
            }
        }

        return input.substring(start, end);
    }

    static boolean hasValidOrderOfBrackets(String input) {
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            counter += checkIfCharacterIsBracket(input.charAt(i));
            if (counter < 0) {
                return false;
            }
        }
        return counter == 0;
    }

}

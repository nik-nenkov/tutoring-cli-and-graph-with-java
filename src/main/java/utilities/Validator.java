package utilities;

import java.security.InvalidParameterException;

public final class Validator {

    private static final String VALID_NUMBER_PATTERN =
            "([1-9]+[0-9]*\\.?[0-9]*)";
    private static final String VALID_NUMBER_OR_BRACKET_PATTERN =
            "([1-9]+[0-9]*\\.?[0-9]*)|([(]+)|([)]+)";
    private static final String SINGLE_BRACKETS_CLOSED =
            "^[(]([a-zA-Z0-9])+[)]$";
    private static final String CELL_POSITION_PATTERN =
            "^([A-Z]{1,6})([1-9])([0-9]{0,9})$";

    private static final String DECIMAL_PATTERN =
            "(([1-9]+[0-9]*)?(\\.[0-9]+)?)";

    private static final String OPERATION_PATTERN =
            "([+\\-/*^])";

    private static final String EXPRESSION_PATTERN =
            "^(((([A-Z]{1,6})([1-9])([0-9]{0,9}))|" +
                    "(([1-9]+[0-9]*)?(\\.[0-9]+)?))([\\s]*)([+\\-/*^])?)*$";


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

    static boolean isValidNumber(String candidate) {
        return candidate.matches(VALID_NUMBER_PATTERN);
    }

    static boolean isValidNumberOrBracket(String candidate) {
        return candidate.matches(VALID_NUMBER_OR_BRACKET_PATTERN);
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

    private static boolean hasValidOrderOfBrackets(String input) {
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

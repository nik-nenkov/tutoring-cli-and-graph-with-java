package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @Test
    void isValidCellPosition() {
    }

    @Test
    void isValidExpression() {
        assertTrue(Validator.isValidExpression("4 + 7.954"));
    }

    @Test
    void isValidExpressionSecondTry() {
        assertTrue(Validator.isValidExpression("4 + 7.954/983"));
    }
}
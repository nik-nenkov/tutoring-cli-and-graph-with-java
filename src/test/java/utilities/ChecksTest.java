package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChecksTest {

    @Test
    void isValidCellPosition() {
    }

    @Test
    void isValidExpression() {
        assertTrue(Checks.isValidExpression("4 + 7.954"));
    }

    @Test
    void isValidExpressionSecondTry() {
        assertTrue(Checks.isValidExpression("4 + 7.954/983"));
    }
}
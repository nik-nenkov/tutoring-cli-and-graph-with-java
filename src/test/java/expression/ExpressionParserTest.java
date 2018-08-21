package expression;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static expression.ExpressionParser.isValidCellPosition;
import static expression.ExpressionParser.parseExpressionTree;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    @Test
    void isValidCellPositionTest() {
        assertTrue(isValidCellPosition("A3"));
    }

    @Test
    void isValidCellPositionTestLongStringCorrect() {
        assertTrue(isValidCellPosition("KXVCD3354367"));
    }

    @Test
    void isValidCellPositionTestWrongNumber() {
        assertFalse(isValidCellPosition("A01"));
    }

    @Test
    void isValidCellPositionTestWrongLetters() {
        assertFalse(isValidCellPosition("xkcd7"));
    }

    @Test
    void isValidCellPositionTestLongStringIncorrect() {
        assertFalse(isValidCellPosition("AAUHhudisp8657"));
    }

    @Test
    void parseExpressionTreeTest() {
        Tree et = parseExpressionTree("5");
        assertEquals(BigDecimal.valueOf(5), et.getValue());
    }
}
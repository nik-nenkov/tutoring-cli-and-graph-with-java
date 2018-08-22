package expression;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static expression.ExpressionParser.parseExpressionTree;
import static org.junit.jupiter.api.Assertions.*;
import static utilities.Checks.isValidCellPosition;

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
        ExpressionTree et = parseExpressionTree("5");
        assertEquals(BigDecimal.valueOf(5).setScale(2, RoundingMode.HALF_UP), et.getValue());
    }

    @Test
    void parseExpressionTreeTestTwo() {
        ExpressionTree et = parseExpressionTree("5");
        assertEquals(BigDecimal.valueOf(5).setScale(2, RoundingMode.HALF_UP), et.getValue());
    }

    @Test
    void parseExpressionTreeGetTwoLinesSecond() {
        ExpressionTree et = parseExpressionTree("5 - 4 + 3 * 6 / 8 ^ 14 * 3 + 5");
//        System.out.println(et.getInnerNodes());
//        System.out.println(et.getLeafNodes());
    }

    @Test
    void parseExpressionTreeGetTwoLinesThird() {
        ExpressionTree et = parseExpressionTree("5-4+3*6/8^14*3+5");
//        System.out.println(et.getInnerNodes());
//        System.out.println(et.getLeafNodes());
    }

    @Test
    void parseExpressionTreeGetTwoLines() {
        ExpressionTree et = parseExpressionTree("5 - 4 + 3 * 6 / 8 ^ 14 * 3 + 5");
//        System.out.println(et.getInnerNodes());
//        System.out.println(et.getLeafNodes());
    }
}
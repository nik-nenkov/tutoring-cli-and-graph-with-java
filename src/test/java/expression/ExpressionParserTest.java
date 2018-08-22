package expression;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static expression.ExpressionParser.parse;
import static java.math.RoundingMode.HALF_UP;
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
        ExpressionTree et = parse("5");
        assertEquals(BigDecimal.valueOf(5).setScale(6, HALF_UP), et.getValue());
    }

    @Test
    void parseExpressionTreeTestTwo() {
        ExpressionTree et = parse("5     + 7.0");
        assertEquals(BigDecimal.valueOf(12).setScale(6, HALF_UP), et.getValue());
    }

    @Test
    void parseExpressionTreeGetTwoLinesSecond() {
        ExpressionTree et = parse("5 - 4 + 3 *      " +
                "" +
                "" +


                "                  320 / 8 ^    " +
                "" +
                "" +
                "2 * 3 + 5");

        assertEquals(BigDecimal.valueOf(51).setScale(6, HALF_UP), et.getValue());
//        System.out.println(et.getInnerNodes());
//        System.out.println(et.getLeafNodes());
    }

    @Test
    void parseExpressionTreeGetTwoLinesThirdTry() {
        ExpressionTree et = parse("5 - 4.565 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5");
        assertEquals(BigDecimal.valueOf(5.602409).setScale(6, HALF_UP), et.getValue());

//        System.out.println(et.getInnerNodes());
//        System.out.println(et.getLeafNodes());
    }

    @Test
    void parseExpressionTreeGetTwoLines() {
        ExpressionTree et = parse("5 - 4.565 + 3 * 320.576 / 8 ^ 2 * 3.565 + 5");
        assertEquals(BigDecimal.valueOf(59.006255).setScale(6, HALF_UP), et.getValue());
//        System.out.println(et.getInnerNodes());
//        System.out.println(et.getLeafNodes());
    }


    @Test
    void simpleSum() {
        ExpressionTree et = parse("1 + 3 + 5 + 7 + 4");
        assertEquals(BigDecimal.valueOf(20).setScale(6, HALF_UP), et.getValue());
    }

    @Test
    void multiplicationAndSum() {
        ExpressionTree et = parse("1 * 3 + 5 * 7 * 4");
        assertEquals(BigDecimal.valueOf(143).setScale(6, HALF_UP), et.getValue());
    }

    @Test
    void complicatedDivision() {
        ExpressionTree et = parse("3/4/2");
        assertEquals(BigDecimal.valueOf(0.375).setScale(6, HALF_UP), et.getValue());
    }

    @Test
    void complicatedExponentiation() {
        ExpressionTree et = parse("3^2^2");
        assertEquals(BigDecimal.valueOf(81).setScale(6, HALF_UP), et.getValue());
    }
}
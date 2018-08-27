package expression;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static expression.ExpressionParser.parse;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionParserTest {

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
    }

    @Test
    void parseExpressionTreeGetTwoLinesThirdTry() {
        assertEquals(BigDecimal.valueOf(5.602409).setScale(6, HALF_UP),
                parse("5 - 4.565 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getValue());
    }

    @Test
    void parseExpressionTreeGetTwoLines() {
        assertEquals(BigDecimal.valueOf(59.006255).setScale(6, HALF_UP),
                parse("5 - 4.565 + 3 * 320.576 / 8 ^ 2 * 3.565 + 5").getValue());
    }

    @Test
    void simpleSum() {
        assertEquals(BigDecimal.valueOf(20).setScale(6, HALF_UP),
                parse("1 + 3 + 5 + 7 + 4").getValue());
    }

    @Test
    void multiplicationAndSum() {
        assertEquals(BigDecimal.valueOf(143).setScale(6, HALF_UP),
                parse("1 * 3 + 5 * 7 * 4").getValue());
    }

    @Test
    void complicatedDivision() {
        assertEquals(BigDecimal.valueOf(0.375).setScale(6, HALF_UP),
                parse("3/4/2").getValue());
    }

    @Test
    void complicatedExponentiation() {
        assertEquals(BigDecimal.valueOf(81).setScale(6, HALF_UP),
                parse("3^2^2").getValue());
    }

    @Test
    void hardCoreTest() {
        assertEquals(BigDecimal.valueOf(-414.663910).setScale(6, HALF_UP),
                parse("5 - 4.5^3/4*7^1.5-5/6/2*7 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getValue());
    }

    @Test
    void insaneTestWithBrackets() {
        assertEquals(BigDecimal.valueOf(2.40532081377).setScale(6, HALF_UP),
                parse("3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 ) ) )").getValue());
    }

    @Test
    void insaneTestWithBracketsMultiple() {
        assertEquals(BigDecimal.valueOf(-0.14583333333).setScale(6, HALF_UP),
                parse("(3 - 5 )/ (( 6 / ( 2 * 7 ) + 3 ) * 1) / 4").getValue());
    }

    @Test
    void twoPlusTwo() {
        assertEquals(BigDecimal.valueOf(10).setScale(6, HALF_UP),
                parse("2+((2*(3/3))*4)").getValue());
    }

    @Test
    void twoPlusTwoBracketsFirst() {
        assertEquals(BigDecimal.valueOf(25).setScale(6, HALF_UP),
                parse("2+(4+(3*4)+(2*3)+1)").getValue());
    }
}
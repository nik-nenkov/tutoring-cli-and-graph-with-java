package expression;

import org.junit.jupiter.api.Test;
import table.Table;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionParserTest {
    private final Table myTable = new Table();
    private final ExpressionParser xps = new ExpressionParser(myTable);

    @Test
    void parseExpressionTreeTest() {
        ExpressionTree et = xps.parse("500");
        assertEquals(BigDecimal.valueOf(500), et.getResult());
    }

    @Test
    void parseExpressionTreeTestTwo() {
        ExpressionTree et = xps.parse("5     + 7.0");
        assertEquals(BigDecimal.valueOf(12), et.getResult());
    }

    @Test
    void parseExpressionTreeGetTwoLinesSecond() {
        ExpressionTree et = xps.parse("5 - 4 + 3 *      " +
                "" +
                "" +


                "                  320 / 8 ^    " +
                "" +
                "" +
                "2 * 3 + 5");
        assertEquals(BigDecimal.valueOf(51), et.getResult());
    }

    @Test
    void parseExpressionTreeGetTwoLinesThirdTry() {
        assertEquals(BigDecimal.valueOf(5.602410172),
                xps.parse("5 - 4.565 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getResult());
    }

    @Test
    void parseExpressionTreeGetTwoLines() {
        assertEquals(BigDecimal.valueOf(59.006255),
                xps.parse("5 - 4.565 + 3 * 320.576 / 8 ^ 2 * 3.565 + 5").getResult());
    }

    @Test
    void simpleSum() {
        assertEquals(BigDecimal.valueOf(20),
                xps.parse("1 + 3 + 5 + 7 + 4").getResult());
    }

    @Test
    void multiplicationAndSum() {
        assertEquals(BigDecimal.valueOf(143),
                xps.parse("1 * 3 + 5 * 7 * 4").getResult());
    }

    @Test
    void complicatedDivision() {
        assertEquals(BigDecimal.valueOf(0.375),
                xps.parse("3/4/2").getResult());
    }

    @Test
    void complicatedExponentiation() {
        assertEquals(BigDecimal.valueOf(81),
                xps.parse("3^2^2").getResult());
    }

    @Test
    void hardCoreTest() {
        assertEquals(BigDecimal.valueOf(-414.663910881),
                xps.parse("5 - 4.5^3/4*7^1.5-5/6/2*7 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getResult());
    }

    @Test
    void hardcoreTestComparedToWolframAlpha() {
        assertEquals("-414.66391088112309739572120387982747767914956076884614386727950245738711057117094990125",
                xps.parse("5 - 4.5^3/4*7^1.5-5/6/2*7 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getValue().toString());
    }

    @Test
    void hardcoreTestComparedToWolframAlphaTwo() {
        assertEquals(BigDecimal.valueOf(-414.663910881),
                xps.parse("5 - 4.5^3/4*7^1.5-5/6/2*7 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getResult());
    }

    @Test
    void insaneTestWithBrackets() {
        assertEquals(BigDecimal.valueOf(2.405320814),
                xps.parse("3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 ) ) )").getResult());
    }

    @Test
    void insaneTestWithBracketsMultiple() {
        assertEquals(BigDecimal.valueOf(-0.145833333),
                xps.parse("(3 - 5 )/ (( 6 / ( 2 * 7 ) + 3 ) * 1) / 4").getResult());
    }

    @Test
    void anotherInsaneExpression() {
        assertEquals(BigDecimal.valueOf(-20608009.9888491850195663326),
                xps.parse("(19291232-3.67787^1.345^6.78/13.3213)-6.786324*(8.21232+144.4214)^3.1+0.1 ").getResult());
    }

    @Test
    void twoPlusTwo() {
        assertEquals(BigDecimal.valueOf(10),
                xps.parse("2+((2*(3/3))*4)").getResult());
    }

    @Test
    void twoPlusTwoBracketsFirst() {
        assertEquals(BigDecimal.valueOf(25),
                xps.parse("2+(4+(3*4)+(2*3)+1)").getResult());
    }
}
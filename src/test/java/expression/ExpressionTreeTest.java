package expression;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.junit.jupiter.api.Test;
import table.Table;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionTreeTest {

    private final Table myTable = new Table();
    private final ExpressionParser xps = new ExpressionParser(myTable);

    @Test
    void getExpressionStripEmptySpaces() {
        assertEquals("3-5/(6+3-5/(6+3-5/(6+3)))",
                xps.parse("3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 ) ) )").getExpression());
    }

    @Test
    void getExpression() {
        assertEquals(
                "((5-4.5^3)/4*7^1.5-5)/6/2*7+3*320.576/(8/3)^2^2*3.565+5",
                xps.parse("((5 - 4.5^3)/4*7^1.5-5)/6/2*7 + 3 * 320.576/(8 / 3) ^ 2 ^ 2 * 3.565 + 5")
                        .getExpression()
        );

    }

    @Test
    void precision() {
        assertEquals(BigDecimal.valueOf(252.294341633),
                xps.parse("((5-4.5^3)/4*7^1.5-5)/6/2*7+3*320.58/(8/3)^2*3.57+5")
                        .getResult()
        );
    }

    @Test
    void bigMathPrecision() {
        assertEquals(
                BigDecimal.valueOf(404.61399),
                BigDecimalMath.pow(
                        BigDecimal.valueOf(6.765),
                        BigDecimal.valueOf(3.14),
                        new MathContext(20)).setScale(5, RoundingMode.HALF_UP)
        );
    }

    @Test
    void toSeeIfTreeContainsAllNodesInItsPrivateSet() {
        ExpressionTree et = xps.parse("5 - 100 / (3+7-(88/11))");
        System.out.println(et.getValue() + "\n");
        et.getNodes().forEach(e -> System.out.println(e.getValue()));
    }
}
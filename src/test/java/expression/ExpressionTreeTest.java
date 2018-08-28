package expression;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static expression.ExpressionParser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionTreeTest {

    @Test
    void getExpressionStripEmptySpaces() {
        assertEquals("3-5/(6+3-5/(6+3-5/(6+3)))",
                parse("3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 ) ) )").getExpression());
    }

    @Test
    void getExpression() {
        assertEquals(
                "((5-4.5^3)/4*7^1.5-5)/6/2*7+3*320.58/(8/3)^2^2*3.57+5",
                parse("((5 - 4.5^3)/4*7^1.5-5)/6/2*7 + 3 * 320.576/(8 / 3) ^ 2 ^ 2 * 3.565 + 5")
                        .getExpression()
        );

    }

    @Test
    void precision() {
        assertEquals(BigDecimal.valueOf(252.294341633).setScale(3, RoundingMode.HALF_UP),
                parse("((5-4.5^3)/4*7^1.5-5)/6/2*7+3*320.58/(8/3)^2*3.57+5")
                        .getValue().setScale(3, RoundingMode.HALF_UP)
        );
    }

    @Test
    void bigMathPrecision() {
        assertEquals(
                BigDecimal.valueOf(404.61399).setScale(9, RoundingMode.HALF_UP),
                BigDecimalMath.pow(
                        BigDecimal.valueOf(6.765),
                        BigDecimal.valueOf(3.14),
                        new MathContext(20)).setScale(9, RoundingMode.HALF_UP)
        );
    }
}
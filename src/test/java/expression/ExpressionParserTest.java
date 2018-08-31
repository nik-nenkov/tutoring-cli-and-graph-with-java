package expression;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.util.ArrayList;
//import java.util.List;
//import static expression.ExpressionParser.newCrazyIdea;

class ExpressionParserTest {

    @Test
    void parseExpressionTreeTest() {
        ExpressionTree et = ExpressionParser.parse("500");
        assertEquals(BigDecimal.valueOf(500), et.getResult());
    }

    @Test
    void parseExpressionTreeTestTwo() {
        ExpressionTree et = ExpressionParser.parse("5     + 7.0");
        assertEquals(BigDecimal.valueOf(12), et.getResult());
    }

    @Test
    void parseExpressionTreeGetTwoLinesSecond() {
        ExpressionTree et = ExpressionParser.parse("5 - 4 + 3 *      " +
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
                ExpressionParser.parse("5 - 4.565 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getResult());
    }

    @Test
    void parseExpressionTreeGetTwoLines() {
        assertEquals(BigDecimal.valueOf(59.006255),
                ExpressionParser.parse("5 - 4.565 + 3 * 320.576 / 8 ^ 2 * 3.565 + 5").getResult());
    }

    @Test
    void simpleSum() {
        assertEquals(BigDecimal.valueOf(20),
                ExpressionParser.parse("1 + 3 + 5 + 7 + 4").getResult());
    }

    @Test
    void multiplicationAndSum() {
        assertEquals(BigDecimal.valueOf(143),
                ExpressionParser.parse("1 * 3 + 5 * 7 * 4").getResult());
    }

    @Test
    void complicatedDivision() {
        assertEquals(BigDecimal.valueOf(0.375),
                ExpressionParser.parse("3/4/2").getResult());
    }

    @Test
    void complicatedExponentiation() {
        assertEquals(BigDecimal.valueOf(81),
                ExpressionParser.parse("3^2^2").getResult());
    }

    @Test
    void hardCoreTest() {
        assertEquals(
                BigDecimal
                        .valueOf(-414.663910881),
                ExpressionParser
                        .parse("5 - 4.5^3/4*7^1.5-5/6/2*7 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5")
                        .getResult()
        );
    }

    @Test
    void hardcoreTestComparedToWolframAlpha() {
        assertEquals(
                "-414.66391088112309739572120387982747767914956076884614386727950245738711057117094990125",
                ExpressionParser
                        .parse("5 - 4.5^3/4*7^1.5-5/6/2*7 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5")
                        .getValue()
                        .toString()
        );
    }

    @Test
    void hardcoreTestComparedToWolframAlphaTwo() {
        assertEquals(BigDecimal.valueOf(-414.663910881),
                ExpressionParser.parse("5 - 4.5^3/4*7^1.5-5/6/2*7 + 3 * 320.576/5 / 8 ^ 2 ^ 2 * 3.565 + 5").getResult());
    }

    @Test
    void insaneTestWithBrackets() {
        assertEquals(BigDecimal.valueOf(2.405320814),
                ExpressionParser.parse("3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 - 5 / ( 6 + 3 ) ) )").getResult());
    }

    @Test
    void insaneTestWithBracketsMultiple() {
        assertEquals(BigDecimal.valueOf(-0.145833333),
                ExpressionParser.parse("(3 - 5 )/ (( 6 / ( 2 * 7 ) + 3 ) * 1) / 4").getResult());
    }

    @Test
    void anotherInsaneExpression() {
        assertEquals(
                BigDecimal
                        .valueOf(-20608009.9888491850195663326),
                ExpressionParser
                        .parse("(19291232-3.67787^1.345^6.78/13.3213)-6.786324*(8.21232+144.4214)^3.1+0.1 ")
                        .getResult()
        );
    }

    @Test
    void twoPlusTwo() {
        assertEquals(
                BigDecimal.valueOf(10),
                ExpressionParser.parse("2+((2*(3/3))*4)").getResult());
    }

    @Test
    void twoPlusTwoBracketsFirst() {
        assertEquals(BigDecimal.valueOf(25),
                ExpressionParser.parse("2+(4+(3*4)+(2*3)+1)").getResult());
    }

    @Test
    void someNegativeNumbers() {
        assertEquals(BigDecimal.valueOf(8),
                ExpressionParser.parse("-3+11").getResult());
    }
//
//    @Test
//    void myIdea(){
//        String input = "6-7*3";
//        List<InnerNode> innerNodes = new ArrayList<>();
//        List<Node> leafNodes = new ArrayList<>();
//        newCrazyIdea(input,leafNodes,innerNodes);
//
//        leafNodes.forEach(e-> System.out.println(e.toString()));
//        innerNodes.forEach(e-> System.out.println(e.toString()));
//
//    }
}
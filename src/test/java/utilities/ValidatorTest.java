package utilities;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.Validator.extractTheThingFromInsideTheBrackets;
import static utilities.Validator.isValidCellPosition;

class ValidatorTest {

    @Test
    void isValidCellPositionTest() {
        assertTrue(isValidCellPosition("A3"));
    }

    @Test
    void isValidCellPositionTestLongStringCorrect() {
        assertTrue(isValidCellPosition("KXBCD3354367"));
    }

    @Test
    void isValidCellPositionTestWrongNumber() {
        assertFalse(isValidCellPosition("A01"));
    }

    @Test
    void isValidCellPositionTestWrongLetters() {
        assertFalse(isValidCellPosition("xkCd7"));
    }

    @Test
    void isValidCellPositionTestLongStringIncorrect() {
        assertFalse(isValidCellPosition("AAuHHudDsp8657"));
    }

    @Test
    void isValidExpression() {
        assertTrue(Validator.isValidExpression("4 + 7.954"));
    }

    @Test
    void isValidExpressionSecondTry() {
        assertTrue(Validator.isValidExpression("4 + 7.954/983"));
    }

    @Test
    void toFindStringInsideBrackets() {
        assertEquals("SADS(DSAD)DASD(DASD)DSA",
                Validator.extractTheThingFromInsideTheBrackets("dsadsad(SADS(DSAD)DASD(DASD)DSA)njkbhh"));
    }

    @Test
    void removePartOfString() {
        assertThrows(InvalidParameterException.class,
                () -> extractTheThingFromInsideTheBrackets("(ASDF((G(H)J))KL"));
    }


    @Test
    void toFindFirstStringInsideBrackets() {
        //assertEquals("asd(ASD)asd",Validator.extractTheThingFromInsideTheBrackets("dsadsad(SADS(DSAD)DASD((DASD)DSA)SAD)SAD"));
    }





}
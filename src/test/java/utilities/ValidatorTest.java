package utilities;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.Validator.extractTheThingFromInsideTheBrackets;

class ValidatorTest {

    @Test
    void isValidCellPosition() {
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
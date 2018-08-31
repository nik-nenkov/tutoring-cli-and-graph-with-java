package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilities.Converter.replaceMultipleSignOperators;

class ConverterTest {

    @Test
    void replaceMultipleSignOperatorsFirstTry() {
        assertEquals(
                "-4",
                replaceMultipleSignOperators("---+-+-+++--++4")
        );
    }
}
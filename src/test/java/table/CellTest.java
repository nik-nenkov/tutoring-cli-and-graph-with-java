package table;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    void getContent() {
    }

    @Test
    void setContent() {
    }

    @Test
    void getPosition() {
    }

    @Test
    void getRow() {
        Cell c = new Cell("A32", 44.54);
        Assertions.assertEquals(32,c.getRow());
    }

    @Test
    void getCol() {
        Cell c = new Cell("AA1", "HAPPY");
        Assertions.assertEquals(27,c.getCol());
    }
}
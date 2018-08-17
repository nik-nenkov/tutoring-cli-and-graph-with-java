package table;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void putCell() {
    }

    @Test
    void toPrint() {
        Table t = new Table();
        t.putCell(new Cell<>("E8",1));
        t.putCell(new Cell<>("D1",7));
        t.putCell(new Cell<>("B4",4));
        System.out.println(t.toPrint());
    }
}
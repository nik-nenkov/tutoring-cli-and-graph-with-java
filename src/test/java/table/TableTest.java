package table;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableTest {

    @Test
    void putCell() {
    }

    @Test
    void toPrint() {
        Table t = new Table();
        t.putCell(new Cell("E8", 1));
        t.putCell(new Cell("D1", 7));
        t.putCell(new Cell("B4", 4));
        t.putCell(new Cell("C3", 3));
        System.out.println(t.toPrint());
    }

    @Test
    void toPrintEmptyTable(){
        Table t = new Table();
        System.out.println(t.toPrint());
        assertEquals("\nEmpty table!\n",t.toPrint());
    }
}
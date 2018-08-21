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
//        System.out.println(t.toPrint());
        assertEquals("\n" +
                "   ||  A  |  B  |  C  |  D  |  E  |\n" +
                "==========|=====|=====|=====|=====|\n" +
                " 1 ||     |     |     |  7  |     |\n" +
                "-----------------------------------\n" +
                " 2 ||     |     |     |     |     |\n" +
                "-----------------------------------\n" +
                " 3 ||     |     |  3  |     |     |\n" +
                "-----------------------------------\n" +
                " 4 ||     |  4  |     |     |     |\n" +
                "-----------------------------------\n" +
                " 5 ||     |     |     |     |     |\n" +
                "-----------------------------------\n" +
                " 6 ||     |     |     |     |     |\n" +
                "-----------------------------------\n" +
                " 7 ||     |     |     |     |     |\n" +
                "-----------------------------------\n" +
                " 8 ||     |     |     |     |  1  |\n" +
                "-----------------------------------", t.toPrint());
    }

    @Test
    void toPrintEmptyTable(){
        Table t = new Table();
//        System.out.println(t.toPrint());
        assertEquals("\nEmpty table!\n",t.toPrint());
    }
}
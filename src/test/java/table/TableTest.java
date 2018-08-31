package table;

import dependency.DependencyGraph;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableTest {

    @Test
    void setCellValueBigDecimal() {

        Table t = new Table();
        t.putCell(new Cell("E8", BigDecimal.valueOf(1)));
        t.setCellByValue("E8", BigDecimal.valueOf(13.45));
        assertEquals("13.45", t.getCell("E8").toString());
    }

    @Test
    void toPrint() {
        Table t = new Table();
        t.putCell(new Cell("E8", BigDecimal.valueOf(1)));
        t.putCell(new Cell("D1", BigDecimal.valueOf(7)));
        t.putCell(new Cell("B4", BigDecimal.valueOf(4)));
        t.putCell(new Cell("C3", BigDecimal.valueOf(3)));
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
        t.setCellByExpression("B4", "(9+5)/11-4*(3+7)-1");
        assertEquals("\n" +
                "   ||  A  |           B  |  C  |  D  |  E  |\n" +
                "==========|==============|=====|=====|=====|\n" +
                " 1 ||     |              |     |  7  |     |\n" +
                "--------------------------------------------\n" +
                " 2 ||     |              |     |     |     |\n" +
                "--------------------------------------------\n" +
                " 3 ||     |              |  3  |     |     |\n" +
                "--------------------------------------------\n" +
                " 4 ||     |  -39.727273  |     |     |     |\n" +
                "--------------------------------------------\n" +
                " 5 ||     |              |     |     |     |\n" +
                "--------------------------------------------\n" +
                " 6 ||     |              |     |     |     |\n" +
                "--------------------------------------------\n" +
                " 7 ||     |              |     |     |     |\n" +
                "--------------------------------------------\n" +
                " 8 ||     |              |     |     |  1  |\n" +
                "--------------------------------------------", t.toPrint());
    }

    @Test
    void toPrintWiderColumnFour() {
        Table t = new Table();
        t.putCell(new Cell("E8", BigDecimal.valueOf(1)));
        t.putCell(new Cell("D1", BigDecimal.valueOf(75611)));
        t.putCell(new Cell("B4", BigDecimal.valueOf(4)));
        t.putCell(new Cell("C3", BigDecimal.valueOf(3)));
//        System.out.println(t.toPrint());
        assertEquals("\n" +
                "   ||  A  |  B  |  C  |      D  |  E  |\n" +
                "==========|=====|=====|=========|=====|\n" +
                " 1 ||     |     |     |  75611  |     |\n" +
                "---------------------------------------\n" +
                " 2 ||     |     |     |         |     |\n" +
                "---------------------------------------\n" +
                " 3 ||     |     |  3  |         |     |\n" +
                "---------------------------------------\n" +
                " 4 ||     |  4  |     |         |     |\n" +
                "---------------------------------------\n" +
                " 5 ||     |     |     |         |     |\n" +
                "---------------------------------------\n" +
                " 6 ||     |     |     |         |     |\n" +
                "---------------------------------------\n" +
                " 7 ||     |     |     |         |     |\n" +
                "---------------------------------------\n" +
                " 8 ||     |     |     |         |  1  |\n" +
                "---------------------------------------", t.toPrint());
    }

    @Test
    void toPrintEmptyTable() {
        Table t = new Table();
        assertEquals("\nEmpty table!\n", t.toPrint());
    }

    @Test
    void toPrintWeirdTable() {
        Table t = new Table();
        t.putCell(new Cell("E8", BigDecimal.valueOf(1)));
        t.putCell(new Cell("D1", BigDecimal.valueOf(753436)));
        t.putCell(new Cell("B4", BigDecimal.valueOf(44355656)));
        t.putCell(new Cell("C3", BigDecimal.valueOf(3)));
        assertEquals("\n" +
                "   ||  A  |         B  |  C  |       D  |  E  |\n" +
                "==========|============|=====|==========|=====|\n" +
                " 1 ||     |            |     |  753436  |     |\n" +
                "-----------------------------------------------\n" +
                " 2 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 3 ||     |            |  3  |          |     |\n" +
                "-----------------------------------------------\n" +
                " 4 ||     |  44355656  |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 5 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 6 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 7 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 8 ||     |            |     |          |  1  |\n" +
                "-----------------------------------------------", t.toPrint());

        t.setCellByExpression("B4", "44355656-1");

        assertEquals("\n" +
                "   ||  A  |         B  |  C  |       D  |  E  |\n" +
                "==========|============|=====|==========|=====|\n" +
                " 1 ||     |            |     |  753436  |     |\n" +
                "-----------------------------------------------\n" +
                " 2 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 3 ||     |            |  3  |          |     |\n" +
                "-----------------------------------------------\n" +
                " 4 ||     |  44355655  |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 5 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 6 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 7 ||     |            |     |          |     |\n" +
                "-----------------------------------------------\n" +
                " 8 ||     |            |     |          |  1  |\n" +
                "-----------------------------------------------", t.toPrint());
    }


    @Test
    void putCellWhenNoPreviousRecordOnThisPosition() {
        Table t = new Table();
        t.setCellByExpression("A1", "(10-4)/2");
        assertEquals("\n" +
                "   ||  A  |\n" +
                "==========|\n" +
                " 1 ||  3  |\n" +
                "-----------", t.toPrint());
    }


    @Test
    void toCalculateTable() {
        Table myTable = new Table();
        myTable.setCellByValue("A2", BigDecimal.valueOf(88.0));
        myTable.setCellByExpression("E5", "5 - 100 / (3+7-(A2/11))");
        myTable.setCellByExpression("D3", "E5/13.54^1.876");

        DependencyGraph dg = new DependencyGraph(myTable);
        dg.calculate();

        assertEquals("\n" +
                "   ||   A  |  B  |  C  |          D  |    E  |\n" +
                "===========|=====|=====|=============|=======|\n" +
                " 1 ||      |     |     |             |       |\n" +
                "----------------------------------------------\n" +
                " 2 ||  88  |     |     |             |       |\n" +
                "----------------------------------------------\n" +
                " 3 ||      |     |     |  -0.339075  |       |\n" +
                "----------------------------------------------\n" +
                " 4 ||      |     |     |             |       |\n" +
                "----------------------------------------------\n" +
                " 5 ||      |     |     |             |  -45  |\n" +
                "----------------------------------------------", myTable.toPrint());
    }

    @Test
    void toPrintExpressions() {
        Table myTable = new Table();
        myTable.setCellByValue("A2", BigDecimal.valueOf(88.0));
        myTable.setCellByExpression("E5", "5 - 100 / (3+7-(A2/11))");
        myTable.setCellByExpression("D3", "E5/13.54^1.876");

        DependencyGraph dg = new DependencyGraph(myTable);
        dg.calculate();

        assertEquals("\n" +
                "\tA2=88\n" +
                "\tD3=E5/13.54^1.876\n" +
                "\tE5=5-100/(3+7-(A2/11))", myTable.displayAllExpressions());
    }
}
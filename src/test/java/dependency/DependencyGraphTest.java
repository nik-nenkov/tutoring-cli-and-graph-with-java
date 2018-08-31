package dependency;

import org.junit.jupiter.api.Test;
import table.Table;

import java.math.BigDecimal;

class DependencyGraphTest {

    @Test
    void calculate() {
        Table myTable = new Table();
        myTable.setCellByValue("A2", BigDecimal.valueOf(88.0));
        myTable.setCellByExpression("E5", "5 - 100 / (3+7-(A2/11))");
        myTable.setCellByExpression("D3", "E5/13.54^1.876");
        myTable.getDp().calculate();
    }
}
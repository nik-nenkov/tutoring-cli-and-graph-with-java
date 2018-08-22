package expression;

import expression.exception.InvalidInputException;
import table.Cell;
import table.Table;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utilities.Checks.*;

public class ExpressionParser {


    public static void parse(String lastInput, Table myTable) throws InvalidInputException {
        String position = lastInput.split("=")[0].trim();
        String expression = lastInput.split("=")[1].trim();

        if (isValidCellPosition(position) && isValidExpression(expression)) {
            myTable.putCell(new Cell(position, expression));
        }else{
            throw new InvalidInputException(lastInput);
        }
    }


    static void setInputToCell(String position, String input, Table t){
        t.getCell(position).setContent(input);
    }

    static ExpressionTree parseExpressionTree(String input) {
        // Wherever there are more than one whitespaces, replace them with a single space
        if (input.matches(DECIMAL_PATTERN)) {
            return new ExpressionTree(new LeafNode(input));
        }

        List<String> leaves = Arrays.asList(input.split(OPERATION_PATTERN));
        List<String> inners = Arrays.asList(input.split(DECIMAL_PATTERN));

        List<String> leafNodes = leaves
                .stream()
                .map(String::trim)
                .filter(leaf -> !leaf.equals(""))
                .collect(Collectors.toList());

        List<String> innerNodes = inners
                .stream()
                .map(String::trim)
                .filter(inner -> !inner.equals(""))
                .collect(Collectors.toList());

        //TODO dsa
        System.out.println(leafNodes);
        System.out.println(innerNodes);

        return null;
    }
}

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

    static ExpressionTreeImpl parseExpressionTree(String input) {
        // Wherever there are more than one whitespaces, replace them with a single space
        if (input.matches(DECIMAL_PATTERN)) {
            return new ExpressionTreeImpl(new LeafNode(input));
        }

        String[] leaves = input.split(OPERATION_PATTERN);
        String[] inners = input.split(DECIMAL_PATTERN);

        List<LeafNode> nodeLeaves = Arrays.stream(leaves).map(LeafNode::new).collect(Collectors.toList());
        List<InnerNode> innerNodes = Arrays.stream(inners).map(InnerNode::new).collect(Collectors.toList());

        //TODO dsa

        return null;
    }
}

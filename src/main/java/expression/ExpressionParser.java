package expression;

import table.Table;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utilities.Checks.isValidCellPosition;

public class ExpressionParser {

    private static final String DECIMAL_PATTERN = "^([1-9]+[0-9]*)$";
    private static final String OPERATION_PATTERN = "[+\\-/*^]";

    public static void parse(String lastInput, Table myTable) throws InvalidInputException{
        String candidatePosition = lastInput.split("=")[0];
        if(isValidCellPosition(candidatePosition)){
            //TODO some stuff here
        }else{
            throw new InvalidInputException(lastInput);
        }
    }


    static void setInputToCell(String position, String input, Table t){
        t.getCell(position).setContent(input);
    }

    static ExpressionTree parseExpressionTree(String input) {
        // Wherever there are more than one whitespaces, replace them with a single space

        String[] leaves = input.split(OPERATION_PATTERN);
        String[] inners = input.split(DECIMAL_PATTERN);

        List<LeafNode> nodeLeaves = Arrays.stream(leaves).map(LeafNode::new).collect(Collectors.toList());

        if (input.matches(DECIMAL_PATTERN)) {
            return new ExpressionTree(new LeafNode(input));
        }

        return null;
    }
}

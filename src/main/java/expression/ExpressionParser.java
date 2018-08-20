package expression;

import table.Table;

public class ExpressionParser {
    public static final String CELL_POSITION_MATCH = "^([A-Z]{1,6})([1-9])([0-9]{0,9})$";

    public static void parse(String lastInput, Table myTable) throws InvalidInputException{
        String candidatePosition = lastInput.split("=")[0];
        if(isValidCellPosition(candidatePosition)){
            //TODO some stuff here
        }else{
            throw new InvalidInputException(lastInput);
        }
    }

    static boolean isValidCellPosition(String position){
        return position.matches(CELL_POSITION_MATCH);
    }

    static void setInputToCell(String position, String input, Table t){
        if(input.contains(CELL_POSITION_MATCH)){
            t.getCell(position).setContent(parseExpressionTree(input));
        }else{
            t.getCell(position).setContent(input);
        }
    }

    public static ExpressionTree parseExpressionTree(String input) {
        ExpressionTree et = new ExpressionTree();
        String[] leaves = input.split("[+\\-/*^]");
        return null;
    }
}

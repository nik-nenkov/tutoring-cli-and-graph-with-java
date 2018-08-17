package expression;

import table.Table;

public class ExpressionParser {
    public static void parse(String lastInput, Table myTable) throws InvalidInputException{
        String candidatePosition = lastInput.split("=")[0];
        if(isValidCellPosition(candidatePosition)){
            //TODO some stuff here
        }else{
            throw new InvalidInputException(lastInput);
        }
    }

    static boolean isValidCellPosition(String position){
        return position.matches("^([A-Z]{1,6})([1-9])([0-9]{0,9})$");
    }
}

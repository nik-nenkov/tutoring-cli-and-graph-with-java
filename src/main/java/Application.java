//import expression.ExpressionParser;
//import expression.exception.InvalidInputException;

import table.Table;

import java.math.BigDecimal;
import java.util.Scanner;

import static utilities.Validator.isValidDecimal;

public class Application {

    private static final Table myTable = new Table();
    private static final Scanner scan = new Scanner(System.in);
    private static final String APPLICATION_INFORMATION = "2018 <epam> IS AWESOME!";
    private static final String WELCOME_MESSAGE = "Try /info, /print, /exit or an expression";
    private static final String GOODBYE_MESSAGE = "See ya later User!";
    private static boolean isRunning = true;
    private static String lastInput;

    public static void main(String[] args) {
        print(WELCOME_MESSAGE);
        while(isRunning){
            lastInput = scan.nextLine();
            processQuery();
        }
        print(GOODBYE_MESSAGE);
    }

    private static void processQuery() {
        switch(lastInput){
            case("/print"):
                print(myTable.toPrint());
                break;
            case("/exit"):
                isRunning = false;
                break;
            case("/info"):
                print(APPLICATION_INFORMATION);
                break;
            default:
                processEntry();
                break;
        }
    }

    private static void print(String message){
        System.out.println(message);
    }

    private static void processEntry() {
        if (!lastInput.equals("")) {
            String[] parts = lastInput.split("=");

            if (isValidDecimal(parts[1].trim())) {
                myTable.setCellByValue(
                        parts[0].trim(),
                        BigDecimal.valueOf(Double.parseDouble(parts[1].trim())));
            }
            myTable.setCellByExpression(parts[0].trim(), parts[1]);

        }
    }


}

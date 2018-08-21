package expression.exception;

public class InvalidInputException extends Exception {
    private final String input;

    public InvalidInputException(String lastInput) {
        this.input=lastInput;
    }

    @Override
    public String getMessage() {
        return "Input \""+input+"\" was not recognized as a valid expression.";
    }
}

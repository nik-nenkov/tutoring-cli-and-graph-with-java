package utilities;

public final class Checks {

    private static final String CELL_POSITION_PATTERN = "^([A-Z]{1,6})([1-9])([0-9]{0,9})$";


    public static boolean isValidCellPosition(String position) {
        return position.matches(CELL_POSITION_PATTERN);
    }


}

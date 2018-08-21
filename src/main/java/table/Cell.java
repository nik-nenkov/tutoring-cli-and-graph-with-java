package table;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cell {
    private static final String ERROR_STATE = "Error";
    private static final String INITIALIZED_STATE = "Initialized";
    private static final String NOT_INITIALIZED_STATE = "Not initialized";

    private final String position;
    private String content;
    private int row;
    private int col;
    private BigDecimal value;

    /**
     * Support multiple dependencies for each node:
     */
    private ArrayList<Cell> dependencies;

    /**
     * Support multiple observers on each cell:
     */
    private ArrayList<Cell> observers = new ArrayList<>();

    /**
     * Handling of cell's states:
     */
    private boolean initialized = false;
    private boolean hasError = false;
    private String state = NOT_INITIALIZED_STATE;

    /**
     * Constructor:
     */
    Cell(String position, String content) {
        this.position = position;
        this.content = content;
        this.row = parseRow(position);
        this.col = parseCol(position);
    }

    Cell(String position, long value) {
        this.position = position;
        this.value = BigDecimal.valueOf(value);
        this.row = parseRow(position);
        this.col = parseCol(position);
    }

    Cell(String position, double value) {
        this.position = position;
        this.value = BigDecimal.valueOf(value);
        this.row = parseRow(position);
        this.col = parseCol(position);
    }
    /**
     *   Recalculating only whats relevant(not everything):
     */
    private void recalculate() {
        boolean response = notifyObservers();
    }

    public static boolean isCellRefference(String input) {
        return input.matches("");
    }

    private int parseRow(String position) {
        return Integer.parseInt(position.split("^([A-Z]+)")[1]);
    }

    private int parseCol(String position) {
        String alphabeticalColumn = position.split("([1-9]+)([0-9]*)$")[0];
        int number = 0;
        for (int i = alphabeticalColumn.length() - 1; i >= 0; i--) {
            number = number * 26;
            number = number + ((int) alphabeticalColumn.charAt(i) - 64);
        }
        return number;
    }

    /**
     *   Getters and setters:
     */
    Object getContent() {
        return content;
    }

    private boolean notifyObservers() {
        observers.forEach(Object::notify);
        return false;
    }

    String getPosition() {
        return position;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    public void setContent(String content) {
        if (this.content.getClass().equals(content.getClass())) {
            this.content = content;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

package table;

import java.util.ArrayList;

public class Cell<T> {
    private static final String ERROR_STATE = "Error";
    private static final String INITIALIZED_STATE = "Initialized";
    private static final String NOT_INITIALIZED_STATE = "Not initialized";

    private final String position;
    private Object content;
    private int row;
    private int col;
    private int value;

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
    Cell(String position, T content) {
        this.position = position;
        this.content = content;
        this.row = parseRow(position);
        this.col = parseCol(position);
    }

    /**
     *   Recalculating only whats relevant(not everything):
     */
    private void recalculate() {
        boolean response = notifyObservers();
    }

    private boolean notifyObservers() {
        observers.forEach(c -> c.notify());
        return false;
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

    public void setContent(Object content) {
        if(this.content.getClass().equals(content.getClass())){
            this.content=content;
        }
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

}

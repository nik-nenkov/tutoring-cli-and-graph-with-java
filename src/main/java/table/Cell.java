package table;

public class Cell<T> {

    private final String position;
    private T content;
    private int row;
    private int col;

    Cell(String position, T content) {
        this.position = position;
        this.content = content;
        this.row = parseRow(position);
        this.col = parseCol(position);
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

    T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
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

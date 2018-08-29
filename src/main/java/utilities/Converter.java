package utilities;

public final class Converter {

    public static int toRow(String position) {
        return Integer.parseInt(position.split("^([A-Z]+)")[1]);
    }

    public static int toCol(String position) {
        String alphabeticalColumn = position.split("([1-9]+)([0-9]*)$")[0];
        int number = 0;
        for (int i = alphabeticalColumn.length() - 1; i >= 0; i--) {
            number = number * 26;
            number = number + ((int) alphabeticalColumn.charAt(i) - 64);
        }
        return number;
    }

    public static String toAlphabetical(int i) {
        StringBuilder represent = new StringBuilder();
        while (i != 0) {
            represent.append(Character.toString((char) ((i % 26) + 64)));
            i /= 26;
        }
        return represent.toString();
    }

//    public static String toCellReference(int row, int col){
//        return ""+toAlphabetical(col)+row;
//    }
}

package table;

import java.util.HashMap;

public class Table {

    private int numRows;
    private int numCols;

    private final HashMap<Integer,HashMap<Integer,Cell>> data;

    public Table(int r, int c){
        data = new HashMap<>();
        numRows=r;
        numCols=c;
    }

    public Table() {
        this(0,0);
    }

    synchronized void putCell(Cell c){
        numRows=numRows<c.getRow()?c.getRow():numRows;
        numCols=numCols<c.getCol()?c.getCol():numCols;
        data.putIfAbsent(c.getRow(),new HashMap<>());
        data.get(c.getRow()).put(c.getCol(),c);
    }


    public String toPrint(){

        StringBuilder tableToPrint = new StringBuilder("\n\n   ||");

        for(int k=1;k<=numCols;k++){
            tableToPrint.append("  ").append(toAlphabetical(k)).append("  |");
        }
        tableToPrint.append("\n=====");
        for(int k=1;k<=numCols;k++){
            tableToPrint.append("=====|");
        }


        for(int i =1;i<=numRows;i++){
            tableToPrint.append("\n ").append(i).append(" ||");
            for(int j=1;j<=numCols;j++){
                tableToPrint.append("  ").append(getDataCell(i, j)).append("  |");
            }
            tableToPrint.append("\n-----");
            for(int j=1;j<=numCols;j++){
                tableToPrint.append("------");
            }
        }

        return tableToPrint.toString();
    }

    private String getDataCell(int row, int col) {
        if(data.get(row)!=null){
            return data.get(row).get(col)== null
                    ?" "
                    :data.get(row).get(col).getContent().toString();
        }
        return " ";
    }

    /**
     * @param i is the decimal representation of a number
     * @return the same number but in a 26-base alphabetical representation
     */
    private String toAlphabetical(int i){
        StringBuilder represent = new StringBuilder();
        while(i!=0){
            represent.append( Character.toString((char) ((i%26)+64)));
            i/=26;
        }
        return represent.toString();
    }
}

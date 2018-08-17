import table.Table;

public class Application {
    private static Table t = new Table(2,2);
    public static void main(String[] args) {
        System.out.println(t.toPrint());
    }
}

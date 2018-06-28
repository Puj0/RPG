package game;

public class Printer {

    public void println(String string){
        System.out.println(string);
    }

    public void println(){
        println("");
    }

    private static Printer INSTANCE = new Printer();

    public static Printer getInstance(){
        return Printer.INSTANCE;
    }

    public static Printer getMockInstance(Printer printerMock) {
        INSTANCE = printerMock;
        return INSTANCE;
    }
}

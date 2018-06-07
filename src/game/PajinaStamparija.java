package game;

public class PajinaStamparija {

    public void println(String string){
        System.out.println(string);
    }

    public void println(){
        println("");
    }

    private static  PajinaStamparija INSTANCE = new PajinaStamparija();

    public static PajinaStamparija getInstance(){
        return PajinaStamparija.INSTANCE;
    }

    public static PajinaStamparija getMockInstance(PajinaStamparija pajinaStamparijaMock) {
        INSTANCE = pajinaStamparijaMock;
        return INSTANCE;
    }
}

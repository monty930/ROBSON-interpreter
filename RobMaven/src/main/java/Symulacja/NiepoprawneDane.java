package Symulacja;

public class NiepoprawneDane extends RuntimeException {
    public NiepoprawneDane(String komunikat){
        super("Niepoprawne dane:\n" + komunikat);
    }
}

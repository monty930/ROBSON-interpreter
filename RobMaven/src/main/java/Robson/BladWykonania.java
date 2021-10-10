package Robson;

public class BladWykonania extends Exception {
    public BladWykonania(String komunikat) {
        super("Błąd wykonania:\n" + komunikat);
    }
}

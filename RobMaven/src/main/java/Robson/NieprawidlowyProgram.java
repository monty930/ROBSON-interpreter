package Robson;

public class NieprawidlowyProgram extends Exception {
    public NieprawidlowyProgram(String komunikat) {
        super("Nieprawidłowy program:\n" + komunikat);
    }
}

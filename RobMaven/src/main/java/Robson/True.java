package Robson;
import Symulacja.*;

public class True extends Instrukcja {
    @Override
    public String toString() {
        return "truee()";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        return 1;
    }
}

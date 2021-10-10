package Robson;
import Symulacja.*;

public class False extends Instrukcja {
    @Override
    public String toString() {
        return "falsee()";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        return 0;
    }
}

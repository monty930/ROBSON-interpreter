package Robson;
import Symulacja.*;

public class WiększyRówny extends InstrukcjaDwuArgumentowa {
    WiększyRówny(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public String toString() {
        return "większyRówny(" + this.argument1 + ", " + this.argument2 + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynik;
        if (argument1.wykonaj(zmienne, rob) >= argument2.wykonaj(zmienne, rob))
            wynik = 1;
        else
            wynik = 0;
        return wynik;
    }
}

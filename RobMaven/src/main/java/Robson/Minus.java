package Robson;
import Symulacja.*;

public class Minus extends InstrukcjaDwuArgumentowa {
    Minus(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public String toString() {
        return "minus(" + this.argument1 + ", " + this.argument2 + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynik;
        wynik = (argument1.wykonaj(zmienne, rob) - argument2.wykonaj(zmienne, rob));
        return wynik;
    }
}

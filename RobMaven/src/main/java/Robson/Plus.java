package Robson;
import Symulacja.*;

public class Plus extends InstrukcjaDwuArgumentowa {
    Plus(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public String toString() {
        return "plus(" + this.argument1 + ", " + this.argument2 + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynik1 = argument1.wykonaj(zmienne, rob);
        double wynik2 = argument2.wykonaj(zmienne, rob);
        return (wynik1 + wynik2);
    }
}

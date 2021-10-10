package Robson;
import Symulacja.*;

public class Równy extends InstrukcjaDwuArgumentowa {
    Równy(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    private static boolean dokladnosc(double dokladnosc, double liczba1, double liczba2) {
        return liczba1 + dokladnosc > liczba2 && liczba1 - dokladnosc < liczba2;
    }

    @Override
    public String toString() {
        return "równy(" + this.argument1 + ", " + this.argument2 + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynik;
        if (dokladnosc(0.001, argument1.wykonaj(zmienne, rob), argument2.wykonaj(zmienne, rob)))
            wynik = 1;
        else
            wynik = 0;
        return wynik;
    }
}

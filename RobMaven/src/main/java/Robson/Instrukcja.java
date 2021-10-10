package Robson;
import Symulacja.*;

import static java.lang.System.exit;

public abstract class Instrukcja {
    public abstract double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania;

    private static boolean dokladnosc(double dokladnosc, double liczba1, double liczba2) {
        return liczba1 + dokladnosc > liczba2 && liczba1 - dokladnosc < liczba2;
    }

    // metoda sprawdza czy argument "wynik" jest równy 0 lub 1
    // jesli nie wyrzucany jest wyjątek
    // porównanie odbywa się z dokładnością 0.001.
    protected double sprawdźTyp(double wynik) throws BladWykonania {

        if (dokladnosc(0.001, wynik, 0))
            return 0;
        else if (dokladnosc(0.001, wynik, 1))
            return 1;
        else
            throw new BladWykonania("Instrukcja warunkowa nie otrzymała ani 0 ani 1.");
    }
}

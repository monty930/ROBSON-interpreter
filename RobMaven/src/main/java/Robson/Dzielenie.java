package Robson;
import Symulacja.*;

import static java.lang.System.exit;

public class Dzielenie extends InstrukcjaDwuArgumentowa {
    Dzielenie(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public String toString() {
        return "dzielenie(" + this.argument1 + ", " + this.argument2 + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynik = argument2.wykonaj(zmienne, rob);
        try {
            if (wynik == 0)
                throw new BladWykonania("Próba dzielenia przez 0. Przerwano program.");
        } catch (BladWykonania bladWykonania) {
            exit(1);
        }
        wynik = (argument1.wykonaj(zmienne, rob) / wynik);
        return wynik;
    }
}

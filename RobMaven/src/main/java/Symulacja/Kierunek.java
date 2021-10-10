package Symulacja;

import Robson.BladWykonania;
import Robson.Instrukcja;
import Robson.Zmienne;

import static java.lang.System.exit;

public class Kierunek extends InstrukcjeRoba {
    double wartosc;

    public Kierunek(double wX) {
        this.wartosc = wartosc;
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        int wartosc = (int) this.wartosc;
        if (wartosc != 0 && wartosc != 1 && wartosc != 2 && wartosc != 3) {
            try {
                throw new BladWykonania("Zła wartość kierunku roba.");
            } catch (BladWykonania bladWykonania) {
                exit(1);
            }
        }
        rob.kierunek(wartosc);
        return 0;
    }
}

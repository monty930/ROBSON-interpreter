package Robson;
import Symulacja.*;

public class Blok extends Instrukcja {
    Instrukcja[] instrukcje;

    Blok(Instrukcja[] instrukcje) {
        this.instrukcje = instrukcje;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Instrukcja instrukcja : instrukcje) {
            res.append(instrukcja.toString()).append(";\n");
        }
        return res.toString();
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynik;
        if (instrukcje.length == 0) {
            return 0;
        }
        for (int i = 0; i < instrukcje.length - 1; i++) {
            instrukcje[i].wykonaj(zmienne, rob);
        }
        wynik = instrukcje[instrukcje.length - 1].wykonaj(zmienne, rob);
        return wynik;
    }
}

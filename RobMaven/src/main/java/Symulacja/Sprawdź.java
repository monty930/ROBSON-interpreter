package Symulacja;

import Robson.BladWykonania;
import Robson.Instrukcja;
import Robson.Zmienne;

public class Sprawdź extends InstrukcjeRoba {
    Instrukcja wX;
    Instrukcja wY;

    public Sprawdź(Instrukcja wX, Instrukcja wY) {
        this.wX = wX;
        this.wY = wY;
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        Plansza plansza = rob.plansza();
        double wX = this.wX.wykonaj(zmienne, rob);
        double wY = this.wY.wykonaj(zmienne, rob);

        int współrzędnaX = współrzędneModulo((int) wX, 'x', rob);
        int współrzędnaY = współrzędneModulo((int) wY, 'x', rob);

        if(plansza.pole(współrzędnaX, współrzędnaY).czyMaJedzenie(rob.numerTury)) return 1;
        return 0;
    }
}

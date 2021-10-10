package Symulacja;

import Robson.Instrukcja;
import Robson.Zmienne;

public class Wąchaj extends InstrukcjeRoba {
    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        Plansza plansza = rob.plansza();

        if (plansza.pole(współrzędneModulo(1, 'x', rob), rob.współrzędnaY()).czyMaJedzenie(rob.numerTury())) {
            rob.kierunek(1);
        } else if (plansza.pole(współrzędneModulo(-1, 'x', rob), rob.współrzędnaY()).czyMaJedzenie(rob.numerTury())) {
            rob.kierunek(3);
        } else if (plansza.pole(rob.współrzędnaX(), współrzędneModulo(1, 'y', rob)).czyMaJedzenie(rob.numerTury())) {
            rob.kierunek(0);
        } else if (plansza.pole(rob.współrzędnaX(), współrzędneModulo(-1, 'y', rob)).czyMaJedzenie(rob.numerTury())) {
            rob.kierunek(2);
        }
        return 0;
    }
}

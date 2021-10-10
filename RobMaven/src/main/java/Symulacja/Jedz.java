package Symulacja;

import Robson.Instrukcja;
import Robson.Zmienne;

public class Jedz extends InstrukcjeRoba {
    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        long numerTury = rob.numerTury();
        Plansza plansza = rob.plansza();

        if (plansza.pole(współrzędneModulo(1, 'x', rob),
                współrzędneModulo(1, 'y', rob)).czyMaJedzenie(numerTury)) {
            rob.współrzędnaX(rob.współrzędnaX() + 1);
            rob.współrzędnaY(rob.współrzędnaY() + 1);
        } else if (plansza.pole(współrzędneModulo(1, 'x', rob),
                współrzędneModulo(-1, 'y', rob)).czyMaJedzenie(numerTury)) {
            rob.współrzędnaX(rob.współrzędnaX() + 1);
            rob.współrzędnaY(rob.współrzędnaY() - 1);
        } else if (plansza.pole(współrzędneModulo(-1, 'x', rob),
                współrzędneModulo(1, 'y', rob)).czyMaJedzenie(numerTury)) {
            rob.współrzędnaX(rob.współrzędnaX() - 1);
            rob.współrzędnaY(rob.współrzędnaY() + 1);
        } else if (plansza.pole(współrzędneModulo(-1, 'x', rob),
                współrzędneModulo(-1, 'y', rob)).czyMaJedzenie(numerTury)) {
            rob.współrzędnaX(rob.współrzędnaX() - 1);
            rob.współrzędnaY(rob.współrzędnaY() - 1);
        } else if (plansza.pole(współrzędneModulo(1, 'x', rob),
                rob.współrzędnaY()).czyMaJedzenie(numerTury)) {
            rob.współrzędnaX(rob.współrzędnaX() + 1);
        } else if (plansza.pole(rob.współrzędnaX(),
                współrzędneModulo(1, 'y', rob)).czyMaJedzenie(numerTury)) {
            rob.współrzędnaY(rob.współrzędnaY() + 1);
        } else if (plansza.pole(współrzędneModulo(-1, 'x', rob),
                rob.współrzędnaY()).czyMaJedzenie(numerTury)) {
            rob.współrzędnaX(rob.współrzędnaX() - 1);
        } else if (plansza.pole(rob.współrzędnaX(),
                współrzędneModulo(-1, 'y', rob)).czyMaJedzenie(numerTury)) {
            rob.współrzędnaY(rob.współrzędnaY() - 1);
        } else {
            return 0;
        }
        rob.współrzędnaX(współrzędneModulo(0, 'x', rob));
        rob.współrzędnaY(współrzędneModulo(0, 'y', rob));
        rob.energia(rob.energia() + plansza.pole(rob.współrzędnaX(), rob.współrzędnaY()).oddajJedzenie(numerTury));

        return 0;
    }
}

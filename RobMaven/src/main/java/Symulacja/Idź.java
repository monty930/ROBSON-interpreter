package Symulacja;

import Robson.Instrukcja;
import Robson.Zmienne;

public class Idź extends InstrukcjeRoba {
    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        Plansza plansza = rob.plansza();

        if (rob.kierunek() == 0) { // północ
            rob.współrzędnaY(super.współrzędneModulo(1, 'y', rob));
        }
        if (rob.kierunek() == 1) { // wschód
            rob.współrzędnaX(współrzędneModulo(1, 'x', rob));
        }
        if (rob.kierunek() == 2) { // południe
            rob.współrzędnaY(współrzędneModulo(-1, 'y', rob));
        }
        if (rob.kierunek() == 3) { // zachód
            rob.współrzędnaX(współrzędneModulo(-1, 'x', rob));
        }
        rob.energia(rob.energia() + plansza.pole(rob.współrzędnaX(), rob.współrzędnaY()).oddajJedzenie(rob.numerTury()));

        return 0;
    }
}

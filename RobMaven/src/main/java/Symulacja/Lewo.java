package Symulacja;

import Robson.Zmienne;

public class Lewo extends InstrukcjeRoba {
    public void lewo(Rob rob) {
        rob.kierunek((rob.kierunek() - 1) % 4);
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        lewo(rob);
        return 0;
    }
}

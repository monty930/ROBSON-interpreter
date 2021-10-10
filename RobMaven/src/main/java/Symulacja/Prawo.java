package Symulacja;

import Robson.Zmienne;

public class Prawo extends InstrukcjeRoba {
    public void prawo(Rob rob) {
        rob.kierunek((rob.kierunek() + 1) % 4);
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        prawo(rob);
        return 0;
    }
}

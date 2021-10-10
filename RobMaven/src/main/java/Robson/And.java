package Robson;

import Symulacja.*;

public class And extends InstrukcjaDwuArgumentowa {
    And(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public String toString() {
        return "and(" + this.argument1 + ", " + this.argument2 + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynikArg1 = argument1.wykonaj(zmienne, rob);
        double wynikArg2 = argument2.wykonaj(zmienne, rob);
        if (sprawdźTyp(wynikArg1) * sprawdźTyp(wynikArg2) == 1)
            return 1;
        return 0;
    }
}

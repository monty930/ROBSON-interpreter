package Robson;
import Symulacja.*;

public class Not extends InstrukcjaJednoArgumentowa {
    Not(Instrukcja argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "not(" + this.argument + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynikArg = argument.wykonaj(zmienne, rob);
        if (sprawdźTyp(wynikArg) == 1)
            return 0;
        return 1;
    }
}

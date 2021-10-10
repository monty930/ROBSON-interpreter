package Symulacja;

import Robson.BladWykonania;
import Robson.Instrukcja;
import Robson.Zmienne;

public class PlusWspółrzędnaX extends InstrukcjeRoba{
    Instrukcja argument;

    public PlusWspółrzędnaX(Instrukcja argument) {
        this.argument = argument;
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        return współrzędneModulo((int) argument.wykonaj(zmienne, rob), 'x', rob);
    }
}

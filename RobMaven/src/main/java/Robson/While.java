package Robson;
import Symulacja.*;

public class While extends Instrukcja {
    Instrukcja warunek;
    Blok blok;

    While(Instrukcja warunek, Blok blok) {
        this.warunek = warunek;
        this.blok = blok;
    }

    @Override
    public String toString() {
        return "while(równy(" + this.warunek + ", 1) == 1) {\n" + this.blok + "\n}";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynikArg = warunek.wykonaj(zmienne, rob);
        while (sprawdźTyp(wynikArg) == 1) {
            blok.wykonaj(zmienne, rob);
            wynikArg = warunek.wykonaj(zmienne, rob);
        }

        return 0;
    }
}

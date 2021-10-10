package Robson;
import Symulacja.*;

import java.util.Objects;

public class If extends Instrukcja {
    private final Instrukcja warunek;
    private final Instrukcja blok_prawda;
    private final Instrukcja blok_falsz;

    public If(Instrukcja warunek, Blok blok_prawda, Blok blok_fałsz) {
        this.warunek = warunek;
        this.blok_prawda = blok_prawda;
        this.blok_falsz = blok_fałsz;
    }

    @Override
    public String toString() {
        return "if(równy(" + this.warunek + ", 1) == 1) {\n" + blok_prawda + "\n} else {\n" + Objects.requireNonNullElse(blok_falsz, "liczba(0);\n") + "\n}\n";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynikArg = warunek.wykonaj(zmienne, rob);
        if (sprawdźTyp(wynikArg) == 0) {
            if (blok_falsz != null)
                return blok_falsz.wykonaj(zmienne, rob);
            else
                return 0;
        }
        return blok_prawda.wykonaj(zmienne, rob);
    }
}

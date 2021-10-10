package Robson;
import Symulacja.*;

public class Przypisanie extends Instrukcja {
    final private Instrukcja wartosc;
    final private String nazwa;

    Przypisanie(Instrukcja wartosc, String nazwa) {
        this.wartosc = wartosc;
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "przypisz(\"" + nazwa + "\", " + wartosc + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) throws BladWykonania {
        double wynik;
        wynik = wartosc.wykonaj(zmienne, rob);
        zmienne.nowaGlobalna(nazwa, wynik);
        return wynik;
    }
}

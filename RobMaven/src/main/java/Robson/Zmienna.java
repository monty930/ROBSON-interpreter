package Robson;
import Symulacja.*;

public class Zmienna extends Instrukcja {
    final private String nazwa;

    Zmienna(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "zmienna(\"" + nazwa + "\")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        double wynik;
        wynik = zmienne.dajGlobalną(nazwa);
        return wynik;
    }
}

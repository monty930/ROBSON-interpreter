package Robson;
import Symulacja.*;

public class Liczba extends Instrukcja {
    final private double wartosc;

    Liczba(double wartosc) {
        this.wartosc = wartosc;
    }

    @Override
    public String toString() {
        return "liczba(" + wartosc + ")";
    }

    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        return wartosc;
    }
}

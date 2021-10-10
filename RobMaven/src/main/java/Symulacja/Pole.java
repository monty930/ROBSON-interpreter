package Symulacja;

public class Pole {
    private final long ileRośnieJedzenie;
    private final long ileDajeJedzenie;
    private final boolean czyŻywieniowe;
    private long kiedyZjedzone; // numer tury

    // Metoda sprawdza czy na polu jest gotowe do zjedzenia jedzenie
    // Jeśli jest, jedzenie pozostaje na polu (rob go nie "zjada").
    public boolean czyMaJedzenie(long numerTury) {
        return czyŻywieniowe && kiedyZjedzone + ileRośnieJedzenie <= numerTury;
    }

    // pole żywieniowe
    public Pole(long ileDajeJedzenie, long ileRośnieJedzenie) {
        this.czyŻywieniowe = true;
        kiedyZjedzone = -1 * ileRośnieJedzenie;
        this.ileDajeJedzenie = ileDajeJedzenie;
        this.ileRośnieJedzenie = ileRośnieJedzenie;
    }

    // pole nieżywieniowe
    public Pole() {
        this.czyŻywieniowe = false;
        kiedyZjedzone = 0;
        this.ileDajeJedzenie = 0;
        this.ileRośnieJedzenie = 0;
    }

    // Metoda zwraca ilość energii z zebranego jedzenia.
    // Jeśli jedzenie jeszcze nie dojrzało lub na tym polu w ogóle
    // nie rośnie jedzenie - funkcja zwraca 0.
    long oddajJedzenie(long numerTury) {
        if (czyŻywieniowe && kiedyZjedzone + ileRośnieJedzenie <= numerTury) {
            kiedyZjedzone = numerTury;
            return ileDajeJedzenie;
        }
        return 0;
    }
}

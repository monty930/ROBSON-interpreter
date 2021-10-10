package Robson;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class Zmienne {
    private final Map<String, Double> globalneZmienne = new HashMap<>();

    public void nowaGlobalna(String nazwa, double wartosc) {
        globalneZmienne.put(nazwa, wartosc);
    }

    public double dajGlobalną(String nazwa) {
        Double wynik = globalneZmienne.get(nazwa);
        if (wynik == null) {
            return 0;
        }
        return wynik;
    }

}

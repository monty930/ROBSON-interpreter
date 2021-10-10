import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class instrukcjeJava4 {

private static Map<String, Double> globalneZmienne = new HashMap<>();
private static boolean blad = false;
static double wynik = 0;


public static double wykonaj() {

razy(liczba(1.5), liczba(2.3));
;
return (wynik);
}
public static void main(String[] args) {
if (blad) {
            System.out.println("W programie wystąpił błąd.");
        }
        else
            System.out.println(wykonaj());
}

public static double liczba(double liczba) {
        wynik = liczba;
        return liczba;
    }

    public static double not(double arg) {
        if (arg == 0) {
            wynik = 1;
            return 1;
        }
        else if (arg == 1) {
            wynik = 0;
            return 0;
        }
        blad = true;
        return 0;
    }

    public static double or(double arg1, double arg2) {
        if (arg1 == 1 || arg1 == 0) {
            if (arg2 == 1 || arg2 == 0) {
                if (arg1 + arg2 > 0) {
                    wynik = 1;
                    return 1;
                } else {
                    wynik = 0;
                    return 0;
                }
            }
        }
        blad = true;
        return 0;
    }

    public static double and(double arg1, double arg2) {
        if (arg1 == 1 || arg1 == 0) {
            if (arg2 == 1 || arg2 == 0) {
                if (arg1 * arg2 == 1) {
                    wynik = 1;
                    return 1;
                } else {
                    wynik = 0;
                    return 0;
                }
            }
        }
        blad = true;
        return 0;
    }

    public static double plus(double arg1, double arg2) {
        wynik = arg1 + arg2;
        return arg1 + arg2;
    }

    public static double minus(double arg1, double arg2) {
        wynik = arg1 - arg2;
        return arg1 - arg2;
    }

    public static double razy(double arg1, double arg2) {
        wynik = arg1 * arg2;
        return arg1 * arg2;
    }

    public static double dzielenie(double arg1, double arg2) {
        if (arg2 == 0) {
            blad = true;
            return 0;
        }
        wynik = arg1 / arg2;
        return arg1 / arg2;
    }

    public static double mniejszy(double arg1, double arg2) {
        if (arg1 < arg2) {
            wynik = 1;
            return 1;
        }
        wynik = 0;
        return 0;
    }

    public static double mniejszyRówny(double arg1, double arg2) {
        if (arg1 <= arg2) {
            wynik = 1;
            return 1;
        }
        wynik = 0;
        return 0;
    }

    private static boolean dokładność(double dokladnosc, double liczba1, double liczba2) {
        return liczba1 + dokladnosc > liczba2 && liczba1 - dokladnosc < liczba2;
    }

    public static double większy(double arg1, double arg2) {
        if (arg1 > arg2) {
            wynik = 1;
            return 1;
        }
        wynik = 0;
        return 0;
    }

    public static double równy(double arg1, double arg2) {
        if (dokładność(0.001, arg1, arg2)) {
            wynik = 1;
            return 1;
        }
        wynik = 0;
        return 0;
    }

    public static double większyRówny(double arg1, double arg2) {
        if (arg1 >= arg2) {
            wynik = 1;
            return 1;
        }
        wynik = 0;
        return 0;
    }
    
    public static double truee() {
        wynik = 1;
        return 1;
    }

    public static double falsee() {
        wynik = 0;
        return 0;
    }
    
    public static double zmienna(String nazwa) {
        wynik = globalneZmienne.get(nazwa);
        return wynik;
    }
    
    public static double przypisz(String nazwa, double wartosc) {
        globalneZmienne.put(nazwa, wartosc);
        wynik = wartosc;
        return wynik;
    }
}
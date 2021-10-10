package Robson;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitTesty {
    @Test

    private static boolean dokladnosc(double dokladnosc, double liczba1, double liczba2) {
        return liczba1 + dokladnosc > liczba2 && liczba1 - dokladnosc < liczba2;
    }

    public static void test1() throws BladWykonania, NieprawidlowyProgram, IOException {
        System.out.println("test1 - Fibonacci");
        Robson robson = new Robson();
        robson.fromJSON("pliki_testy/fibonacci.txt");
        robson.toJSON("fibonacci_JSON.txt");
        robson.toJava("fibonacciJava");
        assertTrue(dokladnosc(0.001, 55, robson.wykonaj()));
        System.out.println("OK.");
    }

    public static void test2() throws IOException, BladWykonania, NieprawidlowyProgram {
        System.out.println("test2 - algorytm Euklidesa");
        Robson robson = new Robson();
        robson.fromJSON("pliki_testy/euklides.txt");
        robson.toJSON("euklides_JSON.txt");
        robson.toJava("euklidesJava");
        assertTrue(dokladnosc(0.001, 3, robson.wykonaj()));
        System.out.println("OK.");
    }

    public static void test3() throws IOException, BladWykonania, NieprawidlowyProgram {
        System.out.println("test3 - sprawdzenie pojedynczych instrukcji");
        Robson robson = new Robson();
        robson.fromJSON("pliki_testy/minus_test.txt");
        robson.toJSON("ins-wynik-Java.txt");
        robson.toJava("instrukcjeJava1");
        assertTrue(dokladnosc(0.001, -0.8, robson.wykonaj()));
        System.out.println("minus\t\t\tOK.");

        robson.fromJSON("pliki_testy/plus_test.txt");
        robson.toJSON("ins-wynik-Java.txt");
        robson.toJava("instrukcjeJava2");
        assertTrue(dokladnosc(0.001, 3.8, robson.wykonaj()));
        System.out.println("plus\t\t\tOK.");

        robson.fromJSON("pliki_testy/dzielenie_test.txt");
        robson.toJSON("ins-wynik-Java.txt");
        robson.toJava("instrukcjeJava3");
        assertTrue(dokladnosc(0.001, 0.6522, robson.wykonaj()));
        System.out.println("dzielenie\t\tOK.");

        robson.fromJSON("pliki_testy/razy_test.txt");
        robson.toJSON("ins-wynik-Java.txt");
        robson.toJava("instrukcjeJava4");
        assertTrue(dokladnosc(0.001, 3.45, robson.wykonaj()));
        System.out.println("razy\t\t\tOK.");

        robson.fromJSON("pliki_testy/and-or-not.txt");
        robson.toJSON("ins-wynik-Java.txt");
        robson.toJava("instrukcjeJava5");
        assertTrue(dokladnosc(0.001, 1, robson.wykonaj()));
        System.out.println("and-or-not\t\tOK.");

        robson.fromJSON("pliki_testy/porownywanie_test.txt");
        robson.toJSON("ins-wynik-Java.txt");
        robson.toJava("instrukcjeJava6");
        assertTrue(dokladnosc(0.001, 1, robson.wykonaj()));
        System.out.println("porownywanie\tOK.");
    }
}

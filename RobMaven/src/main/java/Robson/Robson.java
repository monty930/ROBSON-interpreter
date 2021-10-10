package Robson;
/*
Krystyna Gasińska. Programowanie Obiektowe. Zadanie 2.
Implementacja programu ROBSON.
Instrukcja obsługi programu znajduje się w pliku README.txt.
*/

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;

public class Robson {
    Moshi moshi = new Moshi.Builder()
            .add(
                    PolymorphicJsonAdapterFactory.of(Instrukcja.class, "typ")
                            .withSubtype(Blok.class, "Blok")
                            .withSubtype(Minus.class, "Minus")
                            .withSubtype(Plus.class, "Plus")
                            .withSubtype(Razy.class, "Razy")
                            .withSubtype(Dzielenie.class, "Dzielenie")
                            .withSubtype(Liczba.class, "Liczba")
                            .withSubtype(If.class, "If")
                            .withSubtype(MniejszyRówny.class, "<=")
                            .withSubtype(WiększyRówny.class, ">=")
                            .withSubtype(Mniejszy.class, "<")
                            .withSubtype(Większy.class, ">")
                            .withSubtype(Równy.class, "==")
                            .withSubtype(Zmienna.class, "Zmienna")
                            .withSubtype(While.class, "While")
                            .withSubtype(Przypisanie.class, "Przypisanie")
                            .withSubtype(And.class, "And")
                            .withSubtype(Or.class, "Or")
                            .withSubtype(Not.class, "Not")
                            .withSubtype(True.class, "True")
                            .withSubtype(False.class, "False")
            )
            .build();

    JsonAdapter<Instrukcja> jsonAdapter = moshi.adapter(Instrukcja.class);

    Zmienne zmienne = new Zmienne();

    // program zapisany w języku Robson
    Instrukcja programRobson;

    // ten sam program "opakowany" w blok, na potrzeby konwersji na kod w Javie
    Instrukcja programRobsonJava;

    // konwersja programu w języku Robson z pliku filename na obiekt
    // klasy Instrukcja
    void fromJSON(String filename) throws NieprawidlowyProgram {
        String filePath = new File("").getAbsolutePath();
        filename = filePath.concat("/" + filename);
        String str;
        try {
            str = Files.readString(Paths.get(filename));
            programRobson = jsonAdapter.fromJson(str);
            programRobsonJava = jsonAdapter.fromJson(str);
        } catch (NoSuchFileException e) {
            System.out.println("UWAGA, nie ma takiego pliku.");
            throw new NieprawidlowyProgram("Nie ma takiego pliku.");
        } catch (IOException e) {
            throw new NieprawidlowyProgram("Błąd odczytu pliku w formacie JSON.");
        }
    }

    // konwersja programu obiektu klasy Instrukcja na kod w języku
    // Robson. Zapis kodu do pliku filename.
    void toJSON(String filename) throws IOException {
        String json = jsonAdapter.toJson(programRobson);

        String filePath = new File("").getAbsolutePath();
        filename = filePath.concat("/JSON_output/" + filename);

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(json);

        writer.close();
    }

    // zmiana nazwy pliku, który ma być Jawową klasą
    String usuńNielegalneZnaki(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < 'a' || str.charAt(i) > 'z') {
                if (str.charAt(i) < 'A' || str.charAt(i) > 'Z') {
                    if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                        System.out.println("Uwaga. Znaleziono nieprawidłowy znak w" +
                                "nazwie pliku Javy. Zastąpiono znakiem 'a'.");
                        str = str.substring(0, i) + 'a' + str.substring(i + 1);
                    }
                }
            }
        }
        return str;
    }

    // konwersja programu obiektu klasy Instrukcja na kod w języku
    // Java. Zapis kodu do pliku filename.java.
    void toJava(String filename) throws IOException {
        Instrukcja programTemp = new Blok(new Instrukcja[]{programRobsonJava});
        String filename2 = filename;
        if (filename.length() <= 5 || !filename.endsWith(".java")) {
            filename2 = usuńNielegalneZnaki(filename2);
            filename2 += ".java";
        } else {
            filename2 = usuńNielegalneZnaki(filename2);
        }

        filename = filename2;
        filename = filename.substring(0, filename.length() - 5);

        String filePath = new File("").getAbsolutePath();
        filename2 = filePath.concat("/java_output/" + filename2);

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename2));
        String json = "import java.io.*;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n\n" +
                "public class " + filename + " {\n\n" +
                "private static Map<String, Double> globalneZmienne = new HashMap<>();\n" +
                "private static boolean blad = false;\n" +
                "static double wynik = 0;\n\n" +
                "\npublic static double wykonaj() {\n" +
                "\n" +
                programTemp +
                ";\nreturn (wynik);\n}\n" +
                "public static void main(String[] args) {\n" +
                "if (blad) {\n" +
                "            System.out.println(\"W programie wystąpił błąd.\");\n" +
                "        }\n" +
                "        else\n" +
                "            System.out.println(wykonaj());\n" +
                "}\n\n" + funkcjeJavowe() + "\n}";
        writer.write(json);

        writer.close();
    }

    // wykonanie programu zapisanego w języku Robson i przekonwertowanego na
    // obiekt klasy Instrukcja
    double wykonaj() throws BladWykonania {
        return programRobson.wykonaj(zmienne, null);
    }

    // nazwy plików
    static String fromJson = "programJsonIn.txt";
    static String toJava = "programJava.txt";
    static String toJson = "programJsonOut.txt";

    public static void main(String[] args) throws BladWykonania, IOException, NieprawidlowyProgram {
        if (args.length >= 3) {
            fromJson = args[0];
            toJava = args[1];
            toJson = args[2];
        }

        JUnitTesty.test1();
        JUnitTesty.test2();
        JUnitTesty.test3();
    }


    private static String funkcjeJavowe() {
        return "public static double liczba(double liczba) {\n" +
                "        wynik = liczba;\n" +
                "        return liczba;\n" +
                "    }\n" +
                "\n" +
                "    public static double not(double arg) {\n" +
                "        if (arg == 0) {\n" +
                "            wynik = 1;\n" +
                "            return 1;\n" +
                "        }\n" +
                "        else if (arg == 1) {\n" +
                "            wynik = 0;\n" +
                "            return 0;\n" +
                "        }\n" +
                "        blad = true;\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    public static double or(double arg1, double arg2) {\n" +
                "        if (arg1 == 1 || arg1 == 0) {\n" +
                "            if (arg2 == 1 || arg2 == 0) {\n" +
                "                if (arg1 + arg2 > 0) {\n" +
                "                    wynik = 1;\n" +
                "                    return 1;\n" +
                "                } else {\n" +
                "                    wynik = 0;\n" +
                "                    return 0;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        blad = true;\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    public static double and(double arg1, double arg2) {\n" +
                "        if (arg1 == 1 || arg1 == 0) {\n" +
                "            if (arg2 == 1 || arg2 == 0) {\n" +
                "                if (arg1 * arg2 == 1) {\n" +
                "                    wynik = 1;\n" +
                "                    return 1;\n" +
                "                } else {\n" +
                "                    wynik = 0;\n" +
                "                    return 0;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        blad = true;\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    public static double plus(double arg1, double arg2) {\n" +
                "        wynik = arg1 + arg2;\n" +
                "        return arg1 + arg2;\n" +
                "    }\n" +
                "\n" +
                "    public static double minus(double arg1, double arg2) {\n" +
                "        wynik = arg1 - arg2;\n" +
                "        return arg1 - arg2;\n" +
                "    }\n" +
                "\n" +
                "    public static double razy(double arg1, double arg2) {\n" +
                "        wynik = arg1 * arg2;\n" +
                "        return arg1 * arg2;\n" +
                "    }\n" +
                "\n" +
                "    public static double dzielenie(double arg1, double arg2) {\n" +
                "        if (arg2 == 0) {\n" +
                "            blad = true;\n" +
                "            return 0;\n" +
                "        }\n" +
                "        wynik = arg1 / arg2;\n" +
                "        return arg1 / arg2;\n" +
                "    }\n" +
                "\n" +
                "    public static double mniejszy(double arg1, double arg2) {\n" +
                "        if (arg1 < arg2) {\n" +
                "            wynik = 1;\n" +
                "            return 1;\n" +
                "        }\n" +
                "        wynik = 0;\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    public static double mniejszyRówny(double arg1, double arg2) {\n" +
                "        if (arg1 <= arg2) {\n" +
                "            wynik = 1;\n" +
                "            return 1;\n" +
                "        }\n" +
                "        wynik = 0;\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    private static boolean dokładność(double dokladnosc, double liczba1, double liczba2) {\n" +
                "        return liczba1 + dokladnosc > liczba2 && liczba1 - dokladnosc < liczba2;\n" +
                "    }\n" +
                "\n" +
                "    public static double większy(double arg1, double arg2) {\n" +
                "        if (arg1 > arg2) {\n" +
                "            wynik = 1;\n" +
                "            return 1;\n" +
                "        }\n" +
                "        wynik = 0;\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    public static double równy(double arg1, double arg2) {\n" +
                "        if (dokładność(0.001, arg1, arg2)) {\n" +
                "            wynik = 1;\n" +
                "            return 1;\n" +
                "        }\n" +
                "        wynik = 0;\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    public static double większyRówny(double arg1, double arg2) {\n" +
                "        if (arg1 >= arg2) {\n" +
                "            wynik = 1;\n" +
                "            return 1;\n" +
                "        }\n" +
                "        wynik = 0;\n" +
                "        return 0;\n" +
                "    }\n" +
                "    \n" +
                "    public static double truee() {\n" +
                "        wynik = 1;\n" +
                "        return 1;\n" +
                "    }\n" +
                "\n" +
                "    public static double falsee() {\n" +
                "        wynik = 0;\n" +
                "        return 0;\n" +
                "    }\n" +
                "    \n" +
                "    public static double zmienna(String nazwa) {\n" +
                "        wynik = globalneZmienne.get(nazwa);\n" +
                "        return wynik;\n" +
                "    }\n" +
                "    \n" +
                "    public static double przypisz(String nazwa, double wartosc) {\n" +
                "        globalneZmienne.put(nazwa, wartosc);\n" +
                "        wynik = wartosc;\n" +
                "        return wynik;\n" +
                "    }";
    }
}
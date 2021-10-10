package Symulacja;

// Krystyna Gasińska id: 429192, grupa 6.
// Symulacja ewolucji robów. Projekt 1, Programowanie Obiektowe.

// Uwagi i modyfikacje:

// Program zakłada, że wszystkie (liczbowe) parametry są nieujemne.
// Program początkowy robów i spis dostępnych programów powinien być słowem,
// w którym litery należą do zbioru {l, p, i, w, j}.
// Dodatkowo w spisie litery nie powinny się powtarzać.
// Litery w początkowym programie nie muszą występować w spisie.
// Prawdopodobieństwa i ułamek energii rodzica powinny być liczbą
// zmiennoprzecinkową z przedziału [0; 1], pisaną z kropką (np. 0.53).
// Pozostałe parametry są liczbami całkowitymi. Nazwa parametru powinna
// być oddzielona od jego wartości pojedynczą spacją.
//
// Jeśli któryś z powyższych warunków, lub warunków zadania nie zostanie
// spełniony, lub nie zostanie podany prawidłowy plik z parametrami
// lub planszą symulacja nie wykona się, a program zakończy się kodem 1.

// Każdy rob wykonuje cały swój program w każdej turze.
// roby wykonują: najpierw wszystkie swoją pierwszą instrukcję,
// potem wszystkie drugą itd. Gdy roby z krótszymi programami
// wykonają wszystkie swoje instrukcje nie robią nic, aż do końca
// tury. Tura kończy się gdy roby z najdłuższymi programami
// skończą je wykonywać. Roby, jeśli się powielają, robią to na początku
// tury, po odjęciu energii potrzebnej na jej przeżycie, ale przed
// wykonaniem instrukcji (i odjęciem energii potrzebnej na ich wykonanie).
//
// Rob po wykonaniu instrukcji "jedz" nie zmienia kierunku.

import Robson.NieprawidlowyProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Symulacja {
    private static long numerTury = 0;
    private static long ileTur;
    private static long coIleWypisz;
    private static Populacja populacja;

    private static void uzupełnijDane(File planszaPlik, File parametryPlik, String programRobson) {
        Parametry parametry = new Parametry(parametryPlik);

        populacja = new Populacja(parametry.początkowaLiczbaRobów(), parametry.kosztTury(),
                parametry.limitPowielania(), parametry.początkowaEnergia(), parametry.prPowielenia(),
                parametry.prUsunięciaInstrukcji(), parametry.prDodaniaInstrukcji(), parametry.prZmianyInstrukcji(),
                parametry.ułamekEnergiiRodzica(), new Plansza(planszaPlik, parametry.ileDajeJedzenie(),
                parametry.ileRośnieJedzenie()), parametry.spisInstr(), parametry.poczProgr(), programRobson);

        ileTur = parametry.ileTur();
        coIleWypisz = parametry.coIleWypisz();
    }

    public static void symuluj() throws NieprawidlowyProgram {
        if (ileTur == 0) {
            System.out.println("Zerowa liczba tur. Symulacja nie wykonała się." +
                    "\nStan symulacji:\nLiczba robów: " + populacja.ileRobów());
            return;
        }
        populacja.tura(numerTury, numerTury % coIleWypisz == 0);
        while (numerTury < ileTur) {
            numerTury++;
            populacja.tura(numerTury, numerTury % coIleWypisz == 0);
        }
        System.out.println("W sumie urodziło się: " + populacja.ileSięUrodziło() + " robów.");
        System.out.println("W sumie umarło: " + populacja.ileUmarło() + " robów.");
        System.out.println("Na planszy jest: " + populacja.ileRobów() + " robów.");
    }

    public static void main(String[] args) throws NieprawidlowyProgram {
        // wczytywanie danych
        try {
            File planszaPlik = new File(args[0]);
            File parametryPlik = new File(args[1]);
            File programRobsonFile = new File(args[2]);
            String programRobson = Files.readString(Paths.get(String.valueOf(programRobsonFile)));
            uzupełnijDane(planszaPlik, parametryPlik, programRobson);

            if (args.length > 3) {
                throw new NiepoprawneDane("Wprowadzono więcej niż 2 argumenty kompilacji." +
                        " Program zignoruje nadmiar.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Nie wprowadzono nazwy pliku z planszą lub z parametrami\n" +
                    "lub z programem Roba zapisanym w ROBSONIE." +
                    " Przerwano działanie programu.");
            System.exit(1);
        } catch (NiepoprawneDane | IOException e) {
            System.out.println(e.getMessage());
        }

        symuluj();
    }

}

package Symulacja;

import Robson.NieprawidlowyProgram;

import java.util.ArrayList;

public class Populacja {
    private long ileRobów;
    private long ileUmarło = 0;
    private long ileSięUrodziło;
    private final ArrayList<Rob> roby = new ArrayList<>();
    private long minimalnaDługośćProgramu;
    private long maksymalnaDługośćProgramu;
    private float średniaDługośćProgramu;
    private long minimalnaEnergia;
    private long maksymalnaEnergia;
    private float średniaEnergia;
    private long minimalnyWiek;
    private long maksymalnyWiek;
    private float średniWiek;
    private long liczbaPólZŻywnością;
    Plansza plansza;

    public long ileUmarło() {
        return ileUmarło;
    }

    public long ileSięUrodziło() {
        return ileSięUrodziło;
    }

    public long ileRobów() {
        return ileRobów;
    }

    public Populacja(long ileRobów, long kosztTury, long limitPowielania, long początkowaEnergia, double prPowielenia,
                     double prUsunięciaInstrukcji, double prDodaniaInstrukcji, double prZmianyInstrukcji,
                     double ułamekEnergiiRodzica, Plansza plansza, String spisInstrukcji, String początkowyProgram,
                     String programRobson) {
        this.plansza = plansza;
        ileSięUrodziło = ileRobów;
        this.ileRobów = ileRobów;
        for (long i = 0; i < ileRobów; i++) {
            if (początkowaEnergia <= 0) i = ileRobów;
            roby.add(new Rob(kosztTury, limitPowielania, początkowaEnergia, prPowielenia,
                    prUsunięciaInstrukcji, prDodaniaInstrukcji, prZmianyInstrukcji,
                    ułamekEnergiiRodzica, plansza, spisInstrukcji, początkowyProgram, -1, -1, -1, programRobson));
        }

        System.out.println();
    }

    // Metoda wylicza i uzupełnia wartości potrzebne do raportu.
    private void uzupełnij(long numerTury) {
        minimalnyWiek = -1;
        maksymalnyWiek = -1;
        średniWiek = 0;
        for (Rob rob : roby) {
            średniWiek += rob.wiek();
            if (rob.wiek() < minimalnyWiek || minimalnyWiek == -1)
                minimalnyWiek = rob.wiek();
            if (rob.wiek() > maksymalnyWiek)
                maksymalnyWiek = rob.wiek();
        }
        if (roby.size() > 0) średniWiek /= roby.size();

        minimalnaDługośćProgramu = -1;
        maksymalnaDługośćProgramu = -1;
        średniaDługośćProgramu = 0;
        for (Rob rob : roby) {
            średniaDługośćProgramu += rob.długośćProgramu();
            if (rob.długośćProgramu() < minimalnaDługośćProgramu && rob.długośćProgramu() != 0)
                minimalnaDługośćProgramu = rob.długośćProgramu();
            if (minimalnaDługośćProgramu == -1)
                minimalnaDługośćProgramu = rob.długośćProgramu();
            if (rob.długośćProgramu() > maksymalnaDługośćProgramu)
                maksymalnaDługośćProgramu = rob.długośćProgramu();
        }
        if (roby.size() > 0) średniaDługośćProgramu /= roby.size();

        minimalnaEnergia = -1;
        maksymalnaEnergia = -1;
        średniaEnergia = 0;
        for (Rob rob : roby) {
            średniaEnergia += rob.energia();
            if (rob.energia() < minimalnaEnergia && rob.energia() != 0)
                minimalnaEnergia = rob.energia();
            if (minimalnaEnergia == -1)
                minimalnaEnergia = rob.energia();
            if (rob.energia() > maksymalnaEnergia)
                maksymalnaEnergia = rob.energia();
        }
        if (roby.size() > 0) średniaEnergia /= roby.size();

        policzŻywność(numerTury);
    }

    // Metoda wypisuje co zadaną liczbę tur (co_ile_wypisz) oraz przed i po
    // zakończeniu symulacji tekstowy opis bieżącego stanu symulacji.
    private void raport(long numerTury) {
        if (numerTury == 0) {
            System.out.println("Początek symulacji. Na planszy jest " + ileRobów + " robów.");
            if (ileRobów != 0) {
                System.out.println("Każdy z nich ma " + roby.get(0).energia() + " jednostek energii " +
                        "i początkowy program: " + roby.get(0).program());
                policzŻywność(numerTury);
                System.out.println("Na planszy jest " + liczbaPólZŻywnością + " pól z żywnością.");
            }
            return;
        }
        uzupełnij(numerTury);
        System.out.println("Tura " + numerTury + ". Na planszy jest " + ileRobów + " robów.");
        System.out.println("  Do tej pory narodziło się " + ileSięUrodziło + " robów.");
        System.out.println("  Do tej pory umarło " + ileUmarło + " robów.");
        System.out.println("  Pól z jedzeniem jest: " + liczbaPólZŻywnością + ".");
        System.out.print("  Minimalna/średnia/maksymalna długość programu robów wynoszą: " + minimalnaDługośćProgramu);
        System.out.printf("/%.2f/", średniaDługośćProgramu);
        System.out.println(maksymalnaDługośćProgramu);
        System.out.print("  Minimalna/średnia/maksymalna energia robów wynoszą: " + minimalnaEnergia);
        System.out.printf("/%.2f/", średniaEnergia);
        System.out.println(maksymalnaEnergia);
        System.out.print("  Minimalny/średni/maksymalny wiek robów wynoszą: " + minimalnyWiek);
        System.out.printf("/%.2f/", średniWiek);
        System.out.println(maksymalnyWiek);
        System.out.println("  stany robów: ");
        for (int i = 0; i < roby.size(); i++) {
            System.out.print("  rob " + i + ": wiek: " + roby.get(i).wiek());
            System.out.print(", energia: " + roby.get(i).energia());
            System.out.println(", program: " + roby.get(i).program());
        }
    }

    // Metoda oblicza liczbę pól z gotową do zjedzenia żywnością.
    private void policzŻywność(long numerTury) {
        liczbaPólZŻywnością = 0;
        for (int i = 0; i < plansza.liczbaKolumn(); i++) {
            for (int j = 0; j < plansza.liczbaWierszy(); j++) {
                if (plansza.pole(i, j).czyMaJedzenie(numerTury)) {
                    liczbaPólZŻywnością++;
                }
            }
        }
    }

    // Metoda wypisuje co turę podstawowe dane o stanie symulacji.
    private void krótkiRaport(long numerTury) {
        uzupełnij(numerTury);
        if (numerTury == 0) {
            System.out.print("Przed symulacją: ");
        }
        else {
            System.out.print(numerTury + ", ");
        }
        System.out.print("rob: " + ileRobów + ", żyw: " + liczbaPólZŻywnością +
                ", prg: " + minimalnaDługośćProgramu + "/");
        System.out.printf("%.2f", średniaDługośćProgramu);
        System.out.print("/" +
                maksymalnaDługośćProgramu + ", energ: " + minimalnaEnergia + "/");
        System.out.printf("%.2f", średniaEnergia);
        System.out.print("/" + maksymalnaEnergia + ", wiek: " + minimalnyWiek + "/");
        System.out.format("%.2f", średniWiek);
        System.out.println("/" + maksymalnyWiek);
    }

    // Metoda symuluje życie każdego roba po kolei w danej turze.
    public void tura(long numerTury, boolean raport) throws NieprawidlowyProgram {
        if (numerTury == 0) {
            raport(numerTury);
        } else {
            int ileRobówSkończyło = 0;
            ArrayList<Rob> młodeRoby = new ArrayList<>();

            for (int i = 0; i < roby.size(); i++) {
                int wynikRuchu = roby.get(i).ruch();
                if (wynikRuchu == -1) {
                    roby.remove(i);
                    i--;
                    ileRobów--;
                    ileUmarło++;
                } else if (wynikRuchu == 1) {
                    młodeRoby.add(roby.get(i).powielSię());
                    ileRobów++;
                    ileSięUrodziło++;
                }
            }
            for (int i = 0; ileRobówSkończyło < roby.size(); i = (i + 1) % roby.size()) {
                if (!roby.get(i).zakończonoProgram()) {
                    roby.get(i).instrukcja(numerTury);
                    if (roby.get(i).zakończonoProgram()) {
                        ileRobówSkończyło++;
                    }
                }
            }

            roby.addAll(młodeRoby);
            krótkiRaport(numerTury);
            if (raport) {
                raport(numerTury);
            }
        }
    }
}

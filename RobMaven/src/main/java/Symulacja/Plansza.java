package Symulacja;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Plansza {
    private final ArrayList<Pole[]> plansza = new ArrayList<>();
    private int liczbaWierszy = 0;
    private int liczbaKolumn = -1;

    public int liczbaKolumn() {
        return liczbaKolumn;
    }

    public int liczbaWierszy() {
        return liczbaWierszy;
    }

    public Pole pole(int x, int y) {
        return plansza.get(y)[x];
    }

    public Plansza(File planszaPlik, long ileDajeJedzenie, long ileRośnieJedzenie) {
        try {
            Scanner czytajPlanszę = new Scanner(planszaPlik);

            while (czytajPlanszę.hasNextLine()) {
                liczbaWierszy++;
                String linia = czytajPlanszę.nextLine();

                if (liczbaKolumn != -1 && liczbaKolumn != linia.length()) {
                    throw new NiepoprawneDane("Niepoprawna liczba znakow na " +
                            "planszy w wierszu: " + liczbaWierszy + ".");
                }
                plansza.add(new Pole[linia.length()]);
                liczbaKolumn = linia.length();
                for (int i = 0; i < linia.length(); i++) {
                    char znak = linia.charAt(i);
                    if (znak != ' ' && znak != 'x' && znak != '\n') {
                        throw new NiepoprawneDane("Niedozwolony znak w pliku z planszą.");
                    }
                    if (znak == 'x') {
                        plansza.get(liczbaWierszy - 1)[i] = new Pole(ileDajeJedzenie, ileRośnieJedzenie);
                    }
                    if (znak == ' ') {
                        plansza.get(liczbaWierszy - 1)[i] = new Pole();
                    }
                }
            }
            if (liczbaKolumn == 0 || liczbaWierszy == 0) {
                throw new NiepoprawneDane("Brak komórek na planszy.");
            }
            czytajPlanszę.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku z planszą. Symulacja nie wykona się.");
            exit(1);
        } catch (NiepoprawneDane e) {
            System.out.println(e.getMessage() + " Symulacja nie wykona się.");
            exit(1);
        }
    }
}

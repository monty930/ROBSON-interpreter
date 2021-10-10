package Symulacja;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Klasa obsługująca podane w pliku parametry. Sprawdza ich poprawność i
// przechowuje ich wartości.
public class Parametry {
    private final String[] parametrySpis =
            {"ile_tur", "pocz_ile_robów", "pocz_energia", "ile_daje_jedzenie",
             "ile_rośnie_jedzenie", "koszt_tury", "limit_powielania",
             "co_ile_wypisz", "pocz_progr", "spis_instr",
             "ułamek_energii_rodzica", "pr_usunięcia_instr",
             "pr_dodania_instr", "pr_zmiany_instr", "pr_powielenia"};

    // Metoda wyszukuje parametr w spisie parametrów (parametrySpis) po nazwie.
    // Zwraca indeks parametru w spisie, lub -1, jeśli parametr nie został
    // znaleziony.
    private int wyszukaj(String parametr) {
        for (int i = 0; i < parametrySpis.length; i++) {
            if (parametrySpis[i].equals(parametr)) {
                return i;
            }
        }
        return -1;
    }

    private final long[] parametryCałkowite = new long[8];
    private final double[] parametryNiecałkowite = new double[5];
    private String poczProgr;
    private String spisInstr;
    private long długośćSpisu = -1;
    private long długośćProgramu = -1;

    public long ileTur() { return parametryCałkowite[0]; }
    public long początkowaLiczbaRobów() { return parametryCałkowite[1]; }
    public long początkowaEnergia() { return parametryCałkowite[2]; }
    public long ileDajeJedzenie() { return parametryCałkowite[3]; }
    public long ileRośnieJedzenie() { return parametryCałkowite[4]; }
    public long kosztTury() { return parametryCałkowite[5]; }
    public long limitPowielania() { return parametryCałkowite[6]; }
    public long coIleWypisz() { return parametryCałkowite[7]; }
    public String poczProgr() { return poczProgr; }
    public String spisInstr() { return spisInstr; }
    public double prUsunięciaInstrukcji() { return parametryNiecałkowite[1]; }
    public double prDodaniaInstrukcji() { return parametryNiecałkowite[2]; }
    public double prZmianyInstrukcji() { return parametryNiecałkowite[3]; }
    public double prPowielenia() { return parametryNiecałkowite[4]; }
    public double ułamekEnergiiRodzica() { return parametryNiecałkowite[0]; }

    // Metody "czyPoprawneLitery" oraz "sprawdź" sprawdza czy spis instrukcji
    // i początkowy program zawierają jedynie litery ze zbioru {l, p, i, w, j}.
    private boolean czyPoprawneLitery() {
        if (sprawdź(długośćSpisu, spisInstr)) return false;
        return !sprawdź(długośćProgramu, poczProgr);
    }

    private boolean sprawdź(long długośćSpisu, String spisInstr) {
        for (int i = 0; i < długośćSpisu; i++) {
            if (spisInstr.charAt(i) != 'l' &&
                    spisInstr.charAt(i) != 'p' &&
                    spisInstr.charAt(i) != 'i' &&
                    spisInstr.charAt(i) != 'w' &&
                    spisInstr.charAt(i) != 'o' &&
                    spisInstr.charAt(i) != 'j') {
                return true;
            }
        }
        return false;
    }

    // Metoda sprawdza czy dana litera w spisie występuję tylko raz.
    private boolean sprawdźLiteręWSpisie(char litera) {
        boolean wystąpiło = false;
        for (int i = 0; i < długośćSpisu; i++) {
            if (spisInstr.charAt(i) == litera) {
                if (wystąpiło)
                    return false;
                wystąpiło = true;
            }
        }
        return true;
    }

    // Metoda sprawdza poprawność początkowego programu i spisu instrukcji
    private boolean czyPoprawneInstrukcje() {
        if (!czyPoprawneLitery())
            return false;

        if (długośćSpisu > 6) {
            return false;
        }

        return sprawdźLiteręWSpisie('l') &&
                sprawdźLiteręWSpisie('p') &&
                sprawdźLiteręWSpisie('i') &&
                sprawdźLiteręWSpisie('w') &&
                sprawdźLiteręWSpisie('o') &&
                sprawdźLiteręWSpisie('j');
    }

    // Pobieranie i sprawdzanie danych z pliku.
    public Parametry(File parametryCałkowitePlik) {
        Arrays.fill(parametryCałkowite, -1);
        Arrays.fill(parametryNiecałkowite, -1.);

        try {
            long liczbaWierszy = 0;
            Scanner czytajParametry = new Scanner(parametryCałkowitePlik);
            while (czytajParametry.hasNextLine()) {
                liczbaWierszy++;
                String linia = czytajParametry.nextLine();
                Scanner czytajLinię = new Scanner(linia);

                // Program oczekuje liczb zmiennoprzecinkowych
                // pisanych z kropką (np 0.53).
                czytajLinię.useLocale(Locale.US);

                // Parametry powinny być oddzielone (pojedynczą) spacją!
                czytajLinię.useDelimiter(" ");

                if (!czytajLinię.hasNext()) {
                    throw new NiepoprawneDane("Jeden z wierszy " +
                            "w pliku z parametrami jest pusty: linia "
                            + liczbaWierszy + ".");
                }

                String parametr = czytajLinię.next();
                if (!czytajLinię.hasNext()) {
                    throw new NiepoprawneDane("Błąd parametru "
                            + liczbaWierszy + ". Parametr nie został podany, " +
                            "lub użyto niepoprawnego separatora.\nNazwa parametru " +
                            "powinna być odseparowana od parametru pojedynczą spacją.");
                }

                int numerParametru = wyszukaj(parametr);
                if (numerParametru == -1) {
                    throw new NiepoprawneDane("Błędna nazwa parametru: linia "
                            + liczbaWierszy + ".");
                }

                if (numerParametru <= 7) { // Parametr jest całkowity.
                    long wartość = czytajLinię.nextLong();
                    if (wartość < 0) {
                        throw new NiepoprawneDane("Ujemna wartość parametru: "
                                + "linia " + liczbaWierszy + ".");
                    }
                    if (parametryCałkowite[numerParametru] != -1) {
                        throw new NiepoprawneDane("Parametr powtarza się: " +
                                "linia " + liczbaWierszy + ".");
                    }
                    parametryCałkowite[numerParametru] = wartość;
                }
                else if (numerParametru <= 9) { // Parametr jest programem początkowym
                                                // lub spisem instrukcji.
                    String wartość = czytajLinię.next();
                    if (numerParametru == 8) { // Początkowy program (pocz_progr).
                        if (długośćProgramu != -1) {
                            throw new NiepoprawneDane("Parametr powtarza się: linia " + liczbaWierszy + ".");
                        }
                        poczProgr = wartość;
                        długośćProgramu = wartość.length();
                    }
                    else { // Spis instrukcji (spis_instr).
                        if (długośćSpisu != -1) {
                            throw new NiepoprawneDane("Parametr powtarza się: linia " + liczbaWierszy + ".");
                        }
                        spisInstr = wartość;
                        długośćSpisu = wartość.length();
                    }
                }
                else { // numerParametru <= 14, parametr jest niecałkowity.
                    double wartość = czytajLinię.nextDouble();
                    if (wartość < 0 || wartość > 1) {
                        throw new NiepoprawneDane("Niepoprawna wartość prawdopodobieństwa" +
                                " lub ułamku energii rodzica: : linia " + liczbaWierszy + ".\nNależy podać wartość z przedziału [0; 1].");
                    }
                    if (parametryNiecałkowite[numerParametru - 10] != -1.) {
                        throw new NiepoprawneDane("Parametr powtarza się: linia " + liczbaWierszy + ".");
                    }
                    parametryNiecałkowite[numerParametru - 10] = wartość;
                }
                if (czytajLinię.hasNext()) {
                    throw new NiepoprawneDane("Linia nie kończy się po podaniu parametru: linia " + liczbaWierszy + ".");
                }
                czytajLinię.close();
            }

            czytajParametry.close();

            if (liczbaWierszy != parametrySpis.length) {
                throw new NiepoprawneDane("Niepoprawna liczba parametrów.");
            }

            if (!czyPoprawneInstrukcje()) {
                throw new NiepoprawneDane("Niepoprawna litera w początkowym" +
                        "programie robów, w spisie, lub litery w spisie powtarzają się.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku z parametrami.\n" +
                    "Symulacja nie wykona się.");
            System.exit(1);
        } catch (NiepoprawneDane e) {
            System.out.println(e.getMessage() +
                    "\nSymulacja nie wykona się.");
            System.exit(1);
        } catch (InputMismatchException e) {
            System.out.println("Niepoprawny typ parametru. Upewnij się, że parametry" +
                    " zmiennoprzecinkowe (prawdopodobiństwa i ułamki)\nzapisane są w notacji" +
                    " z kropką, np 0.55.\nUpewnij się, że parametr jest odseparowany od " +
                    "jego wartości pojedynczą spacją.\nSymulacja nie wykona się.");
            System.exit(1);
        }
    }
}
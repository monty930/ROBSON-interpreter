package Symulacja;

import Robson.BladWykonania;
import Robson.NieprawidlowyProgram;

import java.util.Random;

import static java.lang.System.exit;

public class Rob {
    private final String spisInstrukcji;
    private final Plansza plansza;
    private final double ułamekEnergiiRodzica;
    private final double prZmianyInstrukcji;
    private final double prDodaniaInstrukcji;
    private final double prUsunięciaInstrukcji;
    private final long kosztTury;
    private final long limitPowielania;
    private final double prPowielenia;
    private int numerInstrukcji = 0;
    private int kierunek; // 0 - północ (góra), 1 - wschód (prawo),
                          // 2 - południe (dół), 3 - zachód (lewo)
    private long energia;
    private final String program;
    private int współrzędnaX;
    private int współrzędnaY;
    private boolean zakończonoProgram = false;
    private long wiek = 0;
    long numerTury = 0;

    public long wiek() {
        return wiek;
    }

    public int współrzędnaX() {
        return współrzędnaX;
    }

    public int współrzędnaY() {
        return współrzędnaY;
    }

    public void współrzędnaX(int współrzędnaX) {
        this.współrzędnaX = współrzędnaX;
    }

    public void współrzędnaY(int współrzędnaY) {
        this.współrzędnaY = współrzędnaY;
    }

    public Plansza plansza() {
        return plansza;
    }

    public int kierunek() {
        return kierunek;
    }

    public long energia() {
        return energia;
    }

    public long numerTury() {
        return numerTury;
    }

    public void energia(long energia) {
        this.energia = energia;
    }

    public long długośćProgramu() {
        return program.length();
    }

    public String program() {
        return program;
    }

    public boolean zakończonoProgram() {
        return zakończonoProgram;
    }

    String programRobson;

    public void kierunek(int kierunek) {
        this.kierunek = kierunek;
    }

    public Rob(long kosztTury, long limitPowielania, long początkowaEnergia, double prPowielenia,
               double prUsunięciaInstrukcji, double prDodaniaInstrukcji, double prZmianyInstrukcji,
               double ułamekEnergiiRodzica, Plansza plansza, String spisInstrukcji, String początkowyProgram,
               int współrzędnaX, int współrzędnaY, int kierunek, String programRobson) {
        this.program = początkowyProgram;
        this.energia = początkowaEnergia;
        this.kosztTury = kosztTury;
        this.limitPowielania = limitPowielania;
        this.prPowielenia = prPowielenia;
        this.prUsunięciaInstrukcji = prUsunięciaInstrukcji;
        this.prDodaniaInstrukcji = prDodaniaInstrukcji;
        this.prZmianyInstrukcji = prZmianyInstrukcji;
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
        this.plansza = plansza;
        this.spisInstrukcji = spisInstrukcji;
        this.programRobson = programRobson;

        // ustalenie losowej pozycji początkowej
        if (współrzędnaX == -1 || współrzędnaY == -1 || kierunek == -1){
            Random r = new Random();
            this.współrzędnaX = r.nextInt(plansza.liczbaKolumn());
            this.współrzędnaY = r.nextInt(plansza.liczbaWierszy());
            this.kierunek = r.nextInt(4);
        } else {
            this.współrzędnaX = współrzędnaX;
            this.współrzędnaY = współrzędnaY;
            this.kierunek = kierunek;
        }
    }

    // Metoda z podanym prawdopodobieństwem zwróci wartość true.
    private boolean wylosuj (double prawdopodobieństwo) {
        Random r = new Random();
        return r.nextDouble() <= prawdopodobieństwo;
    }

    // Metoda tworzy nowego Roba, z nowym programem i energią, zwróconego przeciwnie
    // niż rodzic, na tym samym polu co on.
    public Rob powielSię () {
        int energiaNowegoRoba = (int) ((double )this.energia * ułamekEnergiiRodzica);
        this.energia -= energiaNowegoRoba;
        String nowyProgram = program;
        if (wylosuj(prUsunięciaInstrukcji) && nowyProgram.length() != 0 && spisInstrukcji.length() != 0) {
            nowyProgram = nowyProgram.substring(0, nowyProgram.length() - 1);
        }
        if (wylosuj(prDodaniaInstrukcji) && spisInstrukcji.length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(spisInstrukcji.length());
            nowyProgram += Character.toString(spisInstrukcji.charAt(numerNowejInstrukcji));
        }
        if (wylosuj(prZmianyInstrukcji) && nowyProgram.length() != 0 && spisInstrukcji.length() != 0) {
            Random r = new Random();
            int numerNowejInstrukcji = r.nextInt(spisInstrukcji.length());
            int numerStarejInstrukcji = r.nextInt(nowyProgram.length());
            nowyProgram = nowyProgram.substring(0, numerStarejInstrukcji) +
                    spisInstrukcji.charAt(numerNowejInstrukcji) +
                    nowyProgram.substring(numerStarejInstrukcji + 1);
        }
        return new Rob(kosztTury, limitPowielania, energiaNowegoRoba, prPowielenia, prUsunięciaInstrukcji,
                prDodaniaInstrukcji, prZmianyInstrukcji, ułamekEnergiiRodzica, plansza, spisInstrukcji, nowyProgram,
                współrzędnaX, współrzędnaY, (kierunek + 2) % 4, programRobson);
    }

    public void instrukcja(long numerTury) throws NieprawidlowyProgram {
        if (program.length() == 0 || energia < 1 || numerInstrukcji >= program.length()) {
            zakończonoProgram = true;
            numerInstrukcji = 0;
            return;
        }

        this.numerTury = numerTury;

        try {
            if (program.charAt(numerInstrukcji) == 'l') {
                new RobRobson("{\"typ\":\"Lewo\"}", this);
            } else if (program.charAt(numerInstrukcji) == 'p') {
                new RobRobson("{\"typ\":\"Prawo\"}", this);
            } else if (program.charAt(numerInstrukcji) == 'i') {
                new RobRobson("{\"typ\":\"Idź\"}", this);
            } else if (program.charAt(numerInstrukcji) == 'w') {
                new RobRobson("{\"typ\":\"Wąchaj\"}", this);
            } else if (program.charAt(numerInstrukcji) == 'j') {
                new RobRobson("{\"typ\":\"Jedz\"}", this);
            } else if (program.charAt(numerInstrukcji) == 'o') {
                new RobRobson(programRobson, this);
            }
        } catch (BladWykonania e) {
            System.out.println("Program Roba zawiera błąd. Przerywam symulację.");
            exit(1);
        }
        energia--;
        numerInstrukcji++;
    }

    // metoda zwraca 1 jeśli powstał nowy rob, -1 jeśli obecny rob umarł,
    // 0 w przeciwnym wypadku
    public int ruch () {
        wiek++;
        zakończonoProgram = false;
        // spadek energii
        energia -= kosztTury;
        if (energia < 0) {
            return -1;
        }

        // reprodukcja
        if (wylosuj(prPowielenia) && energia > limitPowielania) {
            return 1;
        }
        return 0;
    }
}

package Symulacja;

import Robson.Instrukcja;

public abstract class InstrukcjeRoba extends Instrukcja {

    public int współrzędneModulo(int liczba, char współrzędna, Rob rob) {
        int współrzędnaX = rob.współrzędnaX();
        int współrzędnaY = rob.współrzędnaY();
        Plansza plansza = rob.plansza();

        if (współrzędna == 'x') {
            if ((współrzędnaX + liczba) % plansza.liczbaKolumn() < 0) {
                return (współrzędnaX + liczba) % plansza.liczbaKolumn() + plansza.liczbaKolumn();
            }
            return (współrzędnaX + liczba) % plansza.liczbaKolumn();
        }
        if (współrzędna == 'y') {
            if ((współrzędnaY + liczba) % plansza.liczbaWierszy() < 0) {
                return (współrzędnaY + liczba) % plansza.liczbaWierszy() + plansza.liczbaWierszy();
            }
            return (współrzędnaY + liczba) % plansza.liczbaWierszy();
        }
        return współrzędnaX + liczba;
    }
}

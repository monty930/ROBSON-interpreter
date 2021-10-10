package Symulacja;

import Robson.Zmienne;

public class WspółrzędnaX extends InstrukcjeRoba{
    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        return rob.współrzędnaX();
    }
}

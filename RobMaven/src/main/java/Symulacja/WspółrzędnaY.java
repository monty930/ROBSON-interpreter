package Symulacja;

import Robson.Zmienne;

public class WspółrzędnaY extends InstrukcjeRoba{
    @Override
    public double wykonaj(Zmienne zmienne, Rob rob) {
        return rob.współrzędnaY();
    }
}

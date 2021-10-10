package Symulacja;

import Robson.*;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;

import java.io.IOException;
import java.util.Objects;

public class RobRobson {
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
                            .withSubtype(Lewo.class, "Lewo")
                            .withSubtype(Prawo.class, "Prawo")
                            .withSubtype(Idź.class, "Idź")
                            .withSubtype(Jedz.class, "Jedz")
                            .withSubtype(Wąchaj.class, "Wąchaj")
                            .withSubtype(Sprawdź.class, "Sprawdź")
                            .withSubtype(WspółrzędnaX.class, "współrzędnaX")
                            .withSubtype(WspółrzędnaY.class, "WspółrzędnaY")
                            .withSubtype(PlusWspółrzędnaX.class, "PlusWspółrzędnaX")
                            .withSubtype(PlusWspółrzędnaY.class, "PlusWspółrzędnaY")
                            .withSubtype(Kierunek.class, "Kierunek")
            )
            .build();

    JsonAdapter<Instrukcja> jsonAdapter = moshi.adapter(Instrukcja.class);

    Zmienne zmienne = new Zmienne();
    Instrukcja program;

    RobRobson(String program, Rob rob) throws NieprawidlowyProgram, BladWykonania {
        try {
            this.program = jsonAdapter.fromJson(program);
            Objects.requireNonNull(this.program).wykonaj(zmienne, rob);
        } catch (IOException e) {
            throw new NieprawidlowyProgram("Błąd odczytu pliku w formacie JSON.");
        }
    }
}

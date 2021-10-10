KRYSTYNA GASIŃSKA zadanie 2 Programowanie Obiektowe

Zadanie ROBSON. Przesłane pliki to projekt MAVEN wraz z regułami i zależnościami potrzebnymi do uruchomienia programu.
Pliki .java z klasami potrzebnymi do zadania 2 są w katalogu: /Robson-zadanie/RobMaven/src/main/java/Robson
Pliki .java z klasami potrzebnymi do BONUSA są w katalogu: /Robson-zadanie/RobMaven/src/main/java/Symulacja
Testy do zadania 2 są w katalogu: /Robson-zadanie/RobMaven/pliki_testy

Pliki outputowe zapisywane są w katalogach: /Robson-zadanie/RobMaven/java_output oraz /Robson-zadanie/RobMaven/JSON_output

pliki potrzebne do uruchomienia symulacji są w katalogu /Robson-zadanie/RobMaven. Są to: plansza.txt, parametry.txt, robson.txt

Katalog /Robson-zadanie/RobMaven/src/main/java/Testy zawiera klasy wygenerowane przez program na przykładowych testach z pliki_testy. Program generuje je do katalogu java_output, a do katalogu Testy zostały skopiowane, aby można było je uruchomić.

Jako argumenty kompilacji należy podać:
- nazwę z programem w formacie JSON, 
- nazwę pliku, do którego zostanie zapisany program jako klasa Javy,
- nazwę pliku, do którego zostanie zapisany program w formacie JSON.

Jeśli argumenty nie zostaną podane, lub zostaną podane jedynie pierwsze 3 parametry, program użyje wartości domyślnych: programJsonIn.txt, programJava.txt, programJsonOut.txt.

Bonus: Po odpaleniu funkcji main znajdującej się w klasie "Symulacja" (zamiast tej w klasie Robson), wykonana zostanie symulacja z poprzedniego zadania, uzupełniona o dodatkową instrukcję oznaczoną literką "o". Rob wykonujący instrukcję "o", wykona program Robson zapisany w podanym w parametrach wejściowych pliku (domyślnie: program.txt).

Symulacji należy podać przy kompilacji następujące parametry:

- nazwę pliku z planszą, na potrzeby symulacji świata Robów,
- nazwę pliku z parametrami, na potrzeby symulacji,
- nazwę pliku z programem dla robów zapisanym w języku ROBSON.

Domyślnie: plansza.txt, parametry.txt, robson.txt

Uruchomić można main w klasie Robson lub symulację (klasa Symulacja) lub pliki Javowe wygenerowane przez mój program.

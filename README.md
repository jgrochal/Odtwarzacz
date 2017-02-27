# Odtwarzacz multimedialny


### Instalacja i uruchamianie
Do uruchomienia programu potrzebny jest telefon działający w systemie Android 5.0 lub nowszym. Ponieważ aplikacja nie jest dostępna do pobrania z oficjalnego sklepu Google Play należy w ustawieniach telefonu zezwolić na instalowanie aplikacji z zewnętrznych źródeł oraz zezwolić na opcję debugowania USB. Kod źródłowy można przy pomocy IDE takich jak np. Intellij IDEA lub Android Studio skompilować i uruchomić na docelowym urządzeniu.

### Obsługa aplikacji
Pliki, które użytkownik chce obsługiwać za pomocą aplikacji, należy umieścić w folderze /testaplikacji/ na karcie pamięci urządzenia. 
Po uruchomieniu aplikacji włącza się jej główny ekran. Użytkownik ma możliwość wybrania jednego z modułów odtwarzacza:

![main screen](https://github.com/jgrochal/Odtwarzacz/blob/master/Screenshot/main.png)

Po wybraniu jednego z modułów wyświetlona zostaje lista plików, jakie mogą zostać obsłużone przez poszczególne moduły aplikacji. Przykladowo po wybraniu przycisku "AUDIO" wyświetlony zostanie ekran:

![file list screen](https://github.com/jgrochal/Odtwarzacz/blob/master/Screenshot/audiolist.png)

Po wybraniu utworu program przechodzi do okna odtwarzacza audio. Można rozpocząć odtwarzanie (przycisk " > "), przerwać je w dowolnym momencie (" || ") lub przewinąć plik dźwiękowy do początku (" << ").

![audio player screen](https://github.com/jgrochal/Odtwarzacz/blob/master/Screenshot/audio.png)

Możliwe jest również przeglądanie umieszczonych w katalogu /testaplikacji/ plików PDF: 

![pdf reader screen](https://github.com/jgrochal/Odtwarzacz/blob/master/Screenshot/pdf.png)

Aplikacja pozwala również na wyświetlanie zdjęć:

![img viewer screen](https://github.com/jgrochal/Odtwarzacz/blob/master/Screenshot/image.png)

### Możliwe wykorzystanie zaimplementowanych rozwiązań
Przykładowym wykorzystaniem zastosowanego w aplikacji rozwiązania w innych projektach może być użycie klasy FileListActivity tak, aby rozszerzyć możliwości aplikacji i pozwolić na wyświetlanie listy oraz otwieranie większej ilości rodzajów plików. Podczas wywołania z głównej aktywności aktywność FileListActivity przyjmuje jako parametr tablicę obiektów klasy String, co jest czynnikiem decydującym o tym, jakie pliki zostaną wyświetlone w aktywności FileListActivity, przykładowo przekazanie następującej tablicy z MainActivity: 
````
b.putStringArray(Constants.SUFFIX_LIST, new String[]{ ".png", ".jpg" });
````
spowoduje, że po wywołaniu aktywności FileListActivity na widoku listy plików zostaną wypisane te o rozszerzeniach .png oraz .jpg.

Rozszerzenie funkcjonalności aplikacji możliwe jest właśnie poprzez dodanie obsługi plików o innych rozszerzeniach oraz zaimplementowanie obsługi tych plików, lub wykorzystanie istniejących bibliotek obsługujących je.

### Opis klas i metod
Aplikacja składa się z pięciu klas - aktywności oraz dwóch pomocniczych, zawierających funkcje statyczne oraz stałe:
````
MainActivity
````
Główna aktywność. Wyświetla cztery przyciski pozwalające użytkownikowi na wybranie jednego z kilku modułów (np. audio, PDF). Posiada metodę onCreate(), w której zaimplementowane jest "nasłuchiwanie" wydarzenia, jakim jest kliknięcie któregoś z przycisków. Naciśnięcie przycisków powoduje uruchomienie aktywności FileListActivity, z odpowiednimi parametrami (rozszerzenia plików obsługiwanych przez odpowiednie podmoduły).


````
FileListActivity
````
Aktywność odpowiedzialna za wyświetlanie widoku listy plików oraz obsługą wydarzenia, jakim jest wybranie (przez dotknięcie) jednego z plików wyświetlanych na liście. Uruchamia odpowiednio jedną z aktywności: AudioPlayerActivity, PdfReaderActivity lub ImageViewerActivity. 
Metoda setListOfFiles() odpowiedzialna jest za wczytanie listy plików z folderu /testaplikacji/ oraz przefiltrowanie ich pod kątem oczekiwanych rozszerzeń. Wykorzystuje metody klasy Utils (getPathList oraz getFileList).


````
AudioPlayerActivity
````
Aktywność obsługująca odtwarzanie wybranego pliku dźwiękowego. Korzysta z biblioteki Androida obsługującej pliki dźwiękowe - MediaPlayer. Aktywność obsługuje nasłuchiwanie trzech przycisków (odtwarzania, przewijania do początku oraz pauzowania). Posiada także pasek postępu informujący wizualnie o obecnej pozycji w odtwarzanym utworze.



````
PdfReaderActivity
````
Aktywność wykorzystująca bibliotekę obsługującą przeglądanie plików PDF - PDF Viewer (https://github.com/jblough/Android-Pdf-Viewer-Library). Z poziomu aktywności możliwe jest przeglądanie kolejnych stron pliku PDF, powiększanie oraz zmniejszanie widoku. 



````
ImageViewerActivity
````
Aktywność korzystająca z klasy ImageView. Metoda setImageBitmap() ustawia wybrany wcześniej plik graficzny na wyświetlany w tej aktywności.



````
Constants
````
Klasa zawierająca stałe obiekty klasy String wykorzystywane przy podejmowaniu decyzji który moduł aplikacji należy uruchomić, a także przy przekazywaniu parametrów podczas wywoływania kolejnych aktywności (jako parametr "klucza" przy korzystaniu z klasy Intent). 


````
Utils
````
Klasa zawierająca statyczne metody: getPathList() - pozwalającą na filtrowanie tablicy plików na podstawie zadanej wcześniej tablicy zawierającej właściwe rozszerzenia (sufiksy) oraz na uzyskanie samej nazwy pliku z bezwzględnej ścieżki pliku - getFileList(). 


### Testy
Testy znajdują sie w pliku UtilsTest. Zadaniem testów było sprawdzenie poprawnego działania metod w klasie Utils. Pierwszy test sprawdza, czy zadana talica obiektów klasy File jest poprawnie filtrowana przy użyciu metody getPathList(). Sprawdzane są przypadki, gdy zadane są dwa, jedno oraz zero rozszerzeń plików. 
Drugi test sprawdza, czy metoda getFileList() uzyskuje poprawne nazwy plików z ich ścieżek. 
    

### Autor
Joanna Grochal

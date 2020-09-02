package ee.bcs.valiit;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

// Enne kui seda tegema hakkad tee ära Lesson 2 (välja arvatud ülesanded 6, 8, 9)
public class Lesson3Hard {
    static Random random = new Random();
    private static int randomNumber = random.nextInt(100);

    public static String numberGame(int x) {
        if (x == randomNumber) {
            return "You guessed right!";
        } else if (x > randomNumber) {
            return "Number you`re looking for is smaller.";
        } else {
            return "Number you are looking for is smaller";
        }
    }
    //0 puhul ütleb, et otsitav number on suurem

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisestka käsklus: evenFibonacci, randomGame või morseCode");
        String kasklus = scanner.nextLine();
        if (kasklus.equalsIgnoreCase("evenFibonacci")) {
            System.out.println("\n" + "Sisesta number milleni soovid Fibonacci jada paaris arvud liita");
            int a = scanner.nextInt();
            System.out.println("\n" + "Arvuni " + a + " on Fibonacci jada paaris numbrite summa on " + evenFibonacci(a));
        } else if (kasklus.equalsIgnoreCase("randomGame")) {
            randomGame();
        } else if (kasklus.equalsIgnoreCase("morseCode")) {
            System.out.println("\n" + "Sisesta tekst mida morse koodi panna");
            System.out.println("Tekst olgu inglise keelses tähestikus ja ilma kirjavahemärkideta.");
            String tekst = scanner.nextLine();
            System.out.println("\n" + morseCode(tekst));
        } else {
            System.out.println("\n" + "Sisestasid tundmatu käskluse");
        }
    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        int fib0 = 0;
        int fib1 = 1;
        int fibn = 0;
        int sum = 0;
        while (fibn <= x) {
            if (fibn % 2 == 0) {
                sum += fibn;
            }
            fibn = fib0 + fib1;
            fib0 = fib1;
            fib1 = fibn;

        }
        return sum;
    }

    public static void randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        System.out.println("\n" + "Arva ära number vahemikus 0-100");
        System.out.println("Sul on 10 katset");
        Random random = new Random();
        int i = random.nextInt(100); //Voi (int) (Math.random() * 100 +1);
        Scanner scanner = new Scanner(System.in);
        int j = 1;
        int katse = 10; //Katseta arv
        boolean vastus = false;
        while (vastus = false || j <= katse) {
            j++;
            int a = scanner.nextInt();
            if (a == i) {
                System.out.println("\n" + "Arvasid õigesti. Vastus oli " + i);
                vastus = true;
            } else if (j > katse) { //See plokk siia, et tarbetuid lisaridasid ei kirjutaks
                System.out.println("\n" + "Katsed said otsa, vastus oli " + i);
            } else if (a > i) {
                System.out.println("\n" + "Otsitav number on väiksem");
            } else {
                System.out.println("\n" + "Otsitav number on suurem");
            }
        }
    }

    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -
        String muudetudTekst = text;
        muudetudTekst = muudetudTekst.toLowerCase();
        int i = 0;
        String vastus = "";

        Map<String, String> morseVastavus = new HashMap<String, String>(); //Alguses Map, siis HashMap
        morseVastavus.put(" ", "   ");
        morseVastavus.put("a", ".- ");
        morseVastavus.put("b", "-... ");
        morseVastavus.put("c", "-.-. ");
        morseVastavus.put("d", "-.. ");
        morseVastavus.put("e", ". ");
        morseVastavus.put("f", "..-. ");
        morseVastavus.put("g", "--. ");
        morseVastavus.put("h", ".... ");
        morseVastavus.put("i", ".. ");
        morseVastavus.put("j", ".--- ");
        morseVastavus.put("k", "-.- ");
        morseVastavus.put("l", ".-.. ");
        morseVastavus.put("m", "-- ");
        morseVastavus.put("n", "-. ");
        morseVastavus.put("o", "--- ");
        morseVastavus.put("p", ".--. ");
        morseVastavus.put("q", "--.- ");
        morseVastavus.put("r", ".-. ");
        morseVastavus.put("s", "... ");
        morseVastavus.put("t", "- ");
        morseVastavus.put("u", "..- ");
        morseVastavus.put("v", "...- ");
        morseVastavus.put("w", ".-- ");
        morseVastavus.put("x", "-..- ");
        morseVastavus.put("y", "-.-- ");
        morseVastavus.put("z", "--.. ");
        morseVastavus.put("1", ".---- ");
        morseVastavus.put("2", "..--- ");
        morseVastavus.put("3", "...-- ");
        morseVastavus.put("4", "....- ");
        morseVastavus.put("5", "..... ");
        morseVastavus.put("6", "-.... ");
        morseVastavus.put("7", "--... ");
        morseVastavus.put("8", "---.. ");
        morseVastavus.put("9", "----. ");
        morseVastavus.put("0", "----- ");

        while (i < muudetudTekst.length()) {
            vastus = vastus + morseVastavus.get(muudetudTekst.substring(i, i + 1));
            //morsevastavus.get võtab tähele vastava morsekoodi lõigu
            //Substring võtab lõigu stringist kohal i kuni j .substring (i,j)
            i++;
        }
        return vastus;
    }

}


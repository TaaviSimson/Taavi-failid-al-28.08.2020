package ee.bcs.valiit;

import java.util.Arrays;
import java.util.Scanner;

public class Lesson3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta käsklus: summma, massiiv, faktoriaal, sordi, tagurpidi või algarv");
        //Sordi, tagurpidi ja algarv veel ei tööta
        String kasklus = scanner.nextLine();
        System.out.println("");

        if (kasklus.equalsIgnoreCase("summa")) {
            System.out.println("Sisesta liidetavad arvud a ja b");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("\n" + "Arvude a ja b summa on " + summa(a, b));
        } else if (kasklus.equalsIgnoreCase("massiiv")) {
            System.out.println("Siseta massivi suurus x (Vahemikus 2-10)");
            int a = scanner.nextInt();
            System.out.println("");
            int[] c = new int[a];
            System.out.println("Sisesta " + a + " numbrit.");
            for (int i = 0; i < c.length; i++) {
                c[i] = scanner.nextInt();
            }
            System.out.println("\n" + "Massiivis olevate numbrite summa on " + massiiv(c));
        } else if (kasklus.equalsIgnoreCase("faktoriaal")) {
            System.out.println("Millise numbri faktoriaali otsid?");
            int a = scanner.nextInt();
            System.out.println("\n" + "Arvu " + a + " faktoriaal on:" + faktoriaal(a));
        } else if (kasklus.equalsIgnoreCase("sordi")) {
            System.out.println("Siseta massivi suurus x (Vahemikus 2-10)");
            int a = scanner.nextInt();
            int[] c = new int[a];
            System.out.println("\n" + "Sisesta " + a + " numbrit.");
            for (int i = 0; i < c.length; i++) {
                c[i] = scanner.nextInt();
            }
            System.out.println("\n" + "Numbrid reastatuna on:");
            System.out.print(Arrays.toString(sordi(c)) + " ");//Arrays to String, muidu ei saa välja printida
        } else if (kasklus.equalsIgnoreCase("tagurpidi")) {
            System.out.println("Siseesta palun sõna");
            String sona = scanner.nextLine();
            System.out.println("\n" + "Sinu sõna tagurpidi on " + tagurpidi(sona));
        } else if (kasklus.equalsIgnoreCase("algarv")) {
            System.out.println("Sisesta arv");
            int a = scanner.nextInt();
            if (algarv(a)) {
                System.out.println("\n" + "Sisestatud arv on algarv");
            } else {
                System.out.println("\n" + "Sisestatud arv ei ole algarv");
            }
        } else {
            System.out.println("Sisestasid vale käskluse");
        }

    }

    public static int summa(int x, int y) {
        // TODO liida kokku ja tagasta x ja y väärtus
        int a = x + y;
        return a;
    }

    public static int massiiv(int[] x) {
        // Todo liida kokku kõik numbrid massivis x
        int sum = 0;
        for (int i = 0; i < x.length; i++) { //Massiivi pikkuse sisendamine. x.length
            sum += x[i];
        }
        int c = sum;
        return c;
    }

    public static int faktoriaal(int x) {
        // TODO tagasta x faktoriaal.
        // Näiteks
        // x = 5
        // return 4*3*2*1 = 24
        int a = 1; //Siin peab olema 1, muidu vastus pidevalt 0
        for (int i = 1; i <= x; i++) {
            a *= i;
        }
        return a;
    }

    public static int[] sordi(int[] a) {//Lahendatud tunnis siiamaani
        // TODO sorteeri massiiv suuruse järgi
        // Näiteks {2, 6, 8, 1}
        // Väljund {1, 2, 6, 8}
        for (int i = 0; i < (a.length - 1); i++) {
            if (a[i] > a[i + 1]) {
                int ajutine = a[i];
                a[i] = a[i + 1];
                a[i + 1] = ajutine;
                i = -1; // Vajalik selleks, et ta hakkaks algusest peale järgmist loopi
            }
        }
        return a;
    }

    public static String tagurpidi(String a) {//a.substring abiks
        // TODO tagasta string tagurpidi
        // Näiteks:
        // a = "Test";
        // return tseT";
        String tagurpidi = "";
        for (int i = (a.length() - 1); i >= 0; i--) { //String pikkuse järel sulud a.length()
            tagurpidi = tagurpidi + a.substring(i, i + 1);
        }
        return tagurpidi; //" "
    }

    public static boolean algarv(int x) {//Motle kodus
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
        int num = x; //Annab numbrile sisendi väärtuse x
        int temp = 0; // Ajutine number jaguvuse kontrollimiseks
        for (int i = 2; i <= num / 2; i++) { // Alustab 2-st ja jagab iga järgmise arvuga
            temp = num % i;
            //Kuna kontrollime jääki, siis n/2 on mõistlik max i väärtus
            //jagades nt 11:6 või 21:11, ei saa jääk 0 tekkida
            if (temp == 0) {
                return false;
            }
        }
        return true;
    }

}
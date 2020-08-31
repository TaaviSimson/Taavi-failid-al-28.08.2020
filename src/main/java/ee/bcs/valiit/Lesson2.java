package ee.bcs.valiit;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta käsklus: fibonacci, exercise1, exercise2, excercise5, exercise8");
        String kasklus = scanner.nextLine();
        if (kasklus.equalsIgnoreCase("fibonacci")) {
            System.out.println("\n" + "Mitmendat Fibonacci jada elementi otsid?");
            int a = scanner.nextInt();
            System.out.println("\n" + "Vastus: " + fibonacci(a));
        } else if (kasklus.equalsIgnoreCase("exercise1")) {
            System.out.println("\n" + "Sisesta 10 täisarvu");
            int[] m = new int[10];
            for (int i = 0; i < m.length; i++) {
                m[i] = scanner.nextInt();
            }
            int[] a = exercise1(m);
            System.out.println("");
            for (int i = 0; i < a.length; i++) {          //(int value : a)
                System.out.print(a[i] + " ");
            }
        } else if (kasklus.equalsIgnoreCase("exercise2")) {
            System.out.println("\n" + "Sisesta number mitut järjestiku paariarvu soovid näha");
            int a = scanner.nextInt();
            exercise2(a);
            //System.out.println(exercise2(a));
        } else if (kasklus.equalsIgnoreCase("exercise5")) {//Ylesanne 5
            System.out.println("\n" + "Sisesta 2 numbrit 1 ja 10 000 vahel");
            int a = scanner.nextInt(); // 1. arv
            int b = scanner.nextInt(); // 2. arv
            int[] vastus = exercise5(a, b);
            System.out.println("\n" + "Vastus:");
            System.out.println(vastus[0] + " " + vastus[1] + " " + vastus[2]);
        } else if (kasklus.equalsIgnoreCase("exercise8")) {
            BigInteger sum = exercise8();
            System.out.println("\n"+"Vastus on: " + sum);
        } else{

        }
        //exercise3(2, 5);
        //exercise7();
    }

    public static int[] exercise1(int[] m) {
        int[] a = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            a[i] = m[m.length - i - 1];
        }
        return a;                                       //a on siinkohal Array
    }

    public static int[] exercise2(int x) {
        //Prindib välja esimesed x paariarvu (naturaalarvud)
        int[] array = new int[x]; // Loome uue array (massiivi) suurusega x
        for (int i = 1; i <= x; i++) { // Kuni masiiv täitub, sooritamme tehet
            array[i - 1] = 2 * i;//Array i-1 seepärast, et hakkame täitma kohast 0
        }
        return array;
    }

    public static void exercise3(int x, int y) {
        // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
        // TODO näiteks x = 3 y = 3
        System.out.println("Sisesta tabeli korrutustabeli laius ja kõrgus x1 ja y1");
        Scanner scanner = new Scanner(System.in); //loob uue skänneri
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        System.out.println("");
        for (int i = 1; i <= x1; i++) {
            for (int j = 1; j <= y1; j++) {
                System.out.print(i * j + " ");//print println asemel jätab ühele reale
            }
            System.out.println();//Tekitab uue rea kui i on iga j-ga läbi korrutatud
        }
        // TODO väljund
        // 1 2 3
        // 2 4 6
        // 3 6 9
    }

    public static int fibonacci(int n) {//OK
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        //System.out.println("Mitmendat Fibonacci jada elementi otsid?");
        //Scanner scanner = new Scanner(System.in);
        //int n1 = scanner.nextInt();
        //System.out.println("");
        int fib1 = 0;
        int fib2 = 1;
        int fibn = 0;
        for (int i = 0; i < n - 2; i++) {
            fibn = fib1 + fib2;// fibn = fib1+ fib2 = 0 + 1 = 1
            //fibn vastab fib3 (kolmas number jadas), seepärast n1-2
            fib1 = fib2; // fib1 = 1
            fib2 = fibn; // fib2 = 1
        }
        //System.out.println("Vastus:");
        //System.out.println(fibn);

        // Tagasta fibonacci jada n element
        return fibn;
    }

    public static int[] exercise5(int a, int b) {//enne oli void
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
        //System.out.println("Sisesta 2 numbrit 1 ja 10 000 vahel");
        //Scanner scanner = new Scanner(System.in);
        //int i = scanner.nextInt(); // 1. arv
        //int j = scanner.nextInt(); // 2. arv
        int i = a; //i asendada a-ga
        int j = b; //j asendada b-ga
        int maxpikkus = 0;// maksimaalne jada pikkus
        for (int k = i; k <= j; k++) {
            int pikkus = 1;// Jada pikkus 1-ni jõudmiseks arvu k puhul
            int k1 = k; //Annab k1-le väärtuse k, ehk number mida vaatleme
            while (k1 != 1) {

                if (k1 % 2 != 0) {
                    k1 = 3 * k1 + 1;
                } else {
                    k1 = k1 / 2;
                }
                pikkus++;//Suurendab k1 jada pikkuse lugerit
            }
            if (maxpikkus < pikkus) { //Võrleb hetkel olevat maksimumpikkust leitud pikkusega
                maxpikkus = pikkus; //Seab maksimumpikkuse leitud suuriima pikkusega võrdseks
            }
        }
        //System.out.println("\n" + "Vastus:");
        //System.out.println(i + " " + j + " " + maxpikkus);
        int[] array = new int[]{i, j, maxpikkus};
        return array;
    }

    public static void exercise6() {
        /*
            Kirjutada Java programm, mis loeb failist visits.txt sisse looduspargi külastajad erinevatel jaanuari päevadel ning
            a) sorteerib külastuspäevad külastajate arvu järgi kasvavalt ning prindib tulemuse konsoolile;
            b) prindib konsoolile päeva, mil külastajaid oli kõige rohkem.
            Faili asukoht tuleb programmile ette anda käsurea parameetrina.
         */
    }

    public static void exercise7() {
        // TODO arvuta kasutades BigDecimali 1.89 * ((394486820340 / 15 ) - 4 )
        BigDecimal a = new BigDecimal(1.89);
        BigDecimal b = new BigDecimal("394486820345");
        BigDecimal c = new BigDecimal("15");
        BigDecimal d = new BigDecimal("4");
        BigDecimal vastus = b.divide(c, 2, RoundingMode.HALF_UP); // RoundingMode.HALF_UP, 2. kohta peale koma
        vastus = vastus.subtract(d);
        a = a.round(new MathContext(3)); //new MathContext, kui varem pole kasutanud
        vastus = a.multiply(vastus);//Multiply korral round ei saa kasutada
        System.out.println(vastus);
    }

    public static BigInteger exercise8() {
        File file = new File("C:\\Users\\opilane\\Downloads\\valiit\\src\\main\\java\\ee\\bcs\\valiit\\150_nums.txt");
        //Käsklus File file = new File() toob soovitava faili programmi
        BigInteger n;                                   //Loob BigInteger n elemendi skaneerimiseks
        BigInteger sum = new BigInteger("0");       //Loob summa jaoks uue BigIntegeri
        try {
            Scanner scanner = new Scanner(file);        //Otsib File file juurest
            while (scanner.hasNext()) {                 //hasNext vaatab kas järgmist rida eksisteerib
                n = scanner.nextBigInteger();           //Liidab summale otsa i liikme
                sum = sum.add(n);                       //Vastusele otsa liitmine BigInteger add meetodiga
            }
            scanner.close();                //Arvatavasti sulgeb skänneri
        } catch (Exception e) {             //Annab teada kui faili leidmine aadressilt on läbikullunud
            e.printStackTrace();            //Peaks teada andma kus ja mis viga esines
        }
        //Õige summa: 77378062799264987173249634924670947389130820063105651135266574
        return sum;
    }


    public static void exercise9() {
        /* TODO
        Sama mis eelmises ülesandes aga ära kasuta BigInt ega BigDecimal klassi
         */
    }

}
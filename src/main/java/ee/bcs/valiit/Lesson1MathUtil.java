package ee.bcs.valiit;

import java.util.Scanner;

public class Lesson1MathUtil {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//Loob uue skänneri
        System.out.println("Sistesta soovitud test: min, max, abs, isEven, min2 või max2");
        String test = scanner.nextLine(); //Loeb millist teksti hakkame tegema
        System.out.println("");//jätab rea vahele, voi "+\n"?

        //String test = "min";
        if (test.equalsIgnoreCase("min")) {
            System.out.println("Sisesta parameetrid a ja b");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("");//jätab rea vahele
            System.out.println("Vastus:");
            System.out.println(min(a, b));
        } else if (test.equalsIgnoreCase("abs")) {
            System.out.println("Sisesta parameetrer a");
            int a = scanner.nextInt();
            System.out.println("");//jätab rea vahele
            System.out.println("Vastus:");
            System.out.println(abs(a));
        } else if (test.equalsIgnoreCase("max")) {
            System.out.println("Sisesta parameetrid a ja b");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("");//jätab rea vahele
            System.out.println("Vastus:");
            System.out.println(max(a, b));
        } else if (test.equalsIgnoreCase("isEven")) {
            System.out.println("Sisesta parameetrer a");
            int a = scanner.nextInt();
            System.out.println("");//jätab rea vahele
            System.out.println("Vastus:");
            System.out.println(isEven(a));
        } else if (test.equalsIgnoreCase("min2")) {
            System.out.println("Sisesta parameetrid a, b ja c");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            System.out.println("");//jätab rea vahele
            System.out.println("Vastus:");
            System.out.println(min2(a, b, c));
        } else {
            System.out.println("Sisesta parameetrid a, b ja c");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            System.out.println("");//jätab rea vahele
            System.out.println("Vastus:");
            System.out.println(max2(a, b, c));
        }

        //System.out.println("Min: "+min(45,35));
        //System.out.println("Max: "+max(69,75));
        //System.out.println("Absoluutväärtus: "+abs(-65));
        //System.out.println("Kas paaris? "+isEven(46));
        //System.out.println(min2(36,45,21));
        //System.out.println(max2(84,96,71));
    }


    public static int min(int a, int b) {
        // TODO tagasta a ja b väikseim väärtus
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }


    public static int max(int a, int b) {
        // TODO tagasta a ja b suurim väärtus
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static int abs(int a) {
        // TODO tagasta a absoluut arv
        // abs(a) korral viskab erroreid
        if (a > 0) {
            return a;
        } else {
            return -a;
        }
    }

    public static boolean isEven(int a) {
        // TODO tagasta true, kui a on paaris arv
        if (a % 2 == 0) {
            return true;
        } else {
            // tagasta false kui a on paaritu arv
            return false;
        }
    }

    public static int min2(int a, int b, int c) {
        // TODO tagasta a, b ja c väikseim väärtus
        if (a < b && a < c) {
            return a;
        } else if (b < c) {
            return b;
        } else {
            return c;
        }
    }

    public static int max2(int a, int b, int c) {
        // TODO tagasta a, b ja c suurim väärtus
        if (a > b && a > c) {
            return a;
        } else if (b > c) {
            return b;
        } else {
            return c;
        }
    }

}

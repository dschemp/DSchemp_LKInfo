package ausrechnen;

public class Ausrechnen_Main {

    public static void main(String[] args) {
        float test = Wurzel.QuadratWurzel(1000);
        int[] primes = Primzahlen.FindPrimes(1000);
        for (int p : primes) {
            System.out.print(p + " ");
        }
        System.out.println();

        System.out.println(test);
    }

}

package ausrechnen;

public class Ausrechnen_Main {

    public static void main(String[] args) {
        float test = Wurzel.Sqrt(16);
        int[] primes = Primzahlen.Range(100);
        for (int p : primes) {
            System.out.print(p + " ");
        }
        System.out.println();

        System.out.println(test);
    }

}

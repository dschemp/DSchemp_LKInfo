package sortieren;

import java.util.Random;

public class Sortieren_Main {

    public static void main(String[] args) {
        // TODO code application logic here
        int[] randomInts = new int[20];

        Random rnd = new Random();
        for (int i = 0; i < randomInts.length; i++) {
            randomInts[i] = rnd.nextInt(100);
        }

        int[] sortedInts = GnomeSort.Sort(randomInts);
        for (int x = 0; x < sortedInts.length; x++) {
            System.out.print(sortedInts[x] + " ");
        }
    } 
}


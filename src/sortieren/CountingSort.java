package sortieren;

public class CountingSort {

    public static int[] Sort(int[] inputArray) {
        int maxNumber = 0;
        int[] countArray, newArray;

        // find max number
        for (int num : inputArray) {
            if (num > maxNumber)
                maxNumber = num;
        }

        // Initialize Count Array
        countArray = new int[maxNumber + 1];
        for (int n = 0; n < countArray.length - 1; n++) {
            countArray[n] = 0;
        }

        // Count Amount of Numbre
        for (int i : inputArray) {
            countArray[i]++;
        }

        // Fill new Array
        newArray = new int[inputArray.length];
        int newArrayIndex = 0;
        for (int x = 0; x < countArray.length; x++) {
            while (countArray[x] > 0) {
                newArray[newArrayIndex] = x;
                countArray[x]--;
                newArrayIndex++;
            }
        }

        // Return new Array
        return newArray;
    }

}

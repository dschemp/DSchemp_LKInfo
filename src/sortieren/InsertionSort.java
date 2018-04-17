package sortieren;

import misc.Swap;

public class InsertionSort {

    public static int[] Sort(int[] arr) {
        for (int x = 1; x < arr.length; x++) {
            for (int y = x; y > 0; y--) {
                if (arr[y-1] > arr[y]) {
                    arr = Swap.SwapInts(arr, y, y-1);
                }
            }
        }
        return arr;
    }

}

package sortieren;

import misc.Swap;

public class GnomeSort {

    public static int[] Sort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (i == 0 || arr[i] >= arr[i - 1]) {
                i++;
            } else {
                arr = Swap.SwapInts(arr, i, i-1);
                i--;
            }
        }
        return arr;
    }

}

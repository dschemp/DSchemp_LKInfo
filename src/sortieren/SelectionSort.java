package sortieren;

import misc.Swap;

public class SelectionSort {

    public static int[] Sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int arrayNum = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i] && arr[arrayNum] > arr[j]) {
                    arrayNum = j;
                }
            }
            arr = Swap.SwapInts(arr, i, arrayNum);
        }
        return arr;
    }

}

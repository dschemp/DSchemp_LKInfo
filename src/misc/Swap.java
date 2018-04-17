package misc;

public class Swap {

    public static int[] SwapInts(int[] arr, int index_one, int index_two) {
        int temp = arr[index_one];
        arr[index_one] = arr[index_two];
        arr[index_two] = temp;
        return arr;
    }

}

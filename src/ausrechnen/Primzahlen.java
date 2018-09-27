package ausrechnen;

public class Primzahlen {

    public static int[] Range(int min, int max) {
        if (min < 2)
            throw new ArithmeticException("Min-Parameter cannot be smaller than 2!");
        else if (min > max || min == max)
            throw new ArithmeticException("Min cannot be larger or equal to max");

        int[] nums = new int[max - min + 1];

        // Fill array
        for (int i = min; i <= max; i++) {
            nums[i - min] = i;
        }

        // go though each number
        for (int j = 2; j <= max; j++) {
            for (int l = 0; l < nums.length; l++) {
                if (nums[l] == 0 || nums[l] == j)
                    continue;

                if (nums[l] % j == 0) {
                    nums[l] = 0;
                }
            }
        }

        //region Find amount of non null (primes) in array
        int count = 0;
        for (int l : nums) {
            if (l != 0)
                count++;
        }
        //endregion

        //region Fill the primes array with the non null numbers from nums
        int[] primes = new int[count];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                primes[index++] = nums[i];
            }
        }
        //endregion

        return primes;
    }

}

package ausrechnen;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;

public class Primzahlen {

    public static int[] FindPrimes(int max) {
        int[] nums = new int[max];

        // Fill array
        for (int i = 0; i < max - 2; i++) {
            nums[i] = i + 2;
        }

        // go though each number
        for (int j = 2; j < max; j++) {
            for (int l = 0; l < nums.length; l++) {
                if (nums[l] == 0 || nums[l] == j)
                    continue;

                if (nums[l] % j == 0) {
                    nums[l] = 0;
                }
            }
        }

        //region Remove all 0's and convert to array
        ArrayList<Integer> primes = new ArrayList<>();
        for (int k : nums) {
            if (k != 0) {
                primes.add(k);
            }
        }

        int[] output = new int[primes.size()];
        for (int p = 0; p < primes.size(); p++) {
            output[p] = primes.get(p);
        }
        //endregion

        return output;
    }


}

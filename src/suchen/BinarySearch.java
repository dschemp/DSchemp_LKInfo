/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suchen;

/**
 *
 * @author daniel.schemp
 */
public class BinarySearch {
    
    public static int SearchIndex(int number, int[] sortedArrayAsc)
    {
        // Mittelindex
        int middle;

        // Leftindex = 0 (Anfang); RightIndex = letztes Element Index (Ende)
        int left = 0, right = sortedArrayAsc.length - 1;

        while (left < right)
        {
            // Mittelindex finden
            middle = (int)Math.floor((left + right) / (double)2);

            if (sortedArrayAsc[middle] < number)
            {
                left = middle++;
                continue;
            }

            if (sortedArrayAsc[middle] > number)
            {
                right = middle--;
                continue;
            }

            return middle;
        }
        return -1;
    }
    
}

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
public class LinearSearch {
    
    public static int SearchIndexFromLeft(int number, int[] sortedArrayAsc)
    {
        for (int i = 0; i<sortedArrayAsc.length; i++) {
            if (sortedArrayAsc[i] == number)
                return i;
        }
        return -1;
    }
    
    public static int SearchIndexFromRight(int number, int[] sortedArrayAsc)
    {
        for (int i = sortedArrayAsc.length - 1; i>0; i--) {
            if (sortedArrayAsc[i] == number)
                return i;
        }
        return -1;
    }
    
}

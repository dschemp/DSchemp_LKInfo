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
public class FindAmountOfNumbers {
    
    public static int FindAmount(int number, int[] sortedArrayAsc) {
        
        int baseIndex = BinarySearch.SearchIndex(number, sortedArrayAsc);        
        if (baseIndex == -1)
            return 0;
        
        int amount = 0;
        for (int x = baseIndex; x < sortedArrayAsc.length; x++) {            
            if (sortedArrayAsc[x] == number)
                amount++;
            else
                break;            
        }
        for (int x = baseIndex; x >= 0; x--) {            
            if (sortedArrayAsc[x] == number)
                amount++;
            else
                break;            
        }
        return amount - 1;
    }
    
}

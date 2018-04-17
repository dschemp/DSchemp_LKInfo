/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Random;

public class CountNumbers {

    public static void main(String[] args) {
        // TODO code application logic here
        int[] ranNums = RandomInts(1000, 10);
        int numberToSearch = 5;
        int amountOfNumberToSearch = SearchAmountOf(numberToSearch, ranNums);
        System.out.println("Anzahl [" + numberToSearch + "]: " + amountOfNumberToSearch);
    }
    
    public static int[] RandomInts(int amount, int maxNumber)
    {
        Random rnd = new Random();
        int[] numbers = new int[amount];
        for (int i = 0; i < amount; i++)
            numbers[i] = rnd.nextInt(maxNumber);

        return numbers;
    }
    
    public static int SearchAmountOf(int number, int[] array)
    {
        int amount = 0;
        for (int i : array) {
            if (i == number)
                amount++;
        }
        return amount;
    }
    
}

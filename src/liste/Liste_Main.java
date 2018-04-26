package liste;

import java.util.Random;

public class Liste_Main {

    public static void main(String[] args) {
        Random rnd = new Random();
        Liste list2 = new Liste();
        for (int i= 0; i < 10; i++) {
            int num = rnd.nextInt(100);
            list2.add(num);
            System.out.println(num);
        }
        System.out.println();
        list2.sort();
        list2.remove();
        list2.remove();
        for (int i = 0; i < list2.getSize(); i++) {
            System.out.println(list2.getAtIndex(i));
        }
    }

}

package liste;

public class Liste_Main {

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        Liste list = new Liste(array);
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getAtIndex(i));
        }

        System.out.println("---");

        for (int x : list.toArray()) {
            System.out.println(x);
        }
    }

}

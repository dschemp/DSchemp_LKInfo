package liste;

public class Liste_Main {

    public static void main(String[] args) {
        Liste list = new Liste();

        list.AddToEnd(2);
        list.AddToEnd(3);
        list.AddToEnd(4);
        int size = list.Get(2);
        System.out.println(size);

    }

}

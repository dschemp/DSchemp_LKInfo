package liste;

public class Liste_Main {

    public static void main(String[] args) {
        Liste list = new Liste();

        list.AddToEnd(2);
        list.AddToEnd(3);
        list.AddToEnd(4);
        System.out.println(list.Get(2));
        System.out.println(list.Get(1));
        System.out.println(list.Get(0));

        System.out.println("Size: " + list.GetSize());
        list.Insert(123, 1);
        System.out.println("Inserted!");
        System.out.println("Size: " + list.GetSize());
        System.out.println(list.Get(1));
    }

}

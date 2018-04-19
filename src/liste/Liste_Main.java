package liste;

public class Liste_Main {

    public static void main(String[] args) {
        Liste list = new Liste();

        list.Add(2);
        list.Add(3);
        list.Add(4);

        System.out.println(list.Get(2));
        System.out.println(list.Get(1));
        System.out.println(list.Get(0));

        System.out.println("Size: " + list.GetSize());
        list.Insert(123, 1);
        System.out.println("Inserted!");
        System.out.println("Size: " + list.GetSize());
        System.out.println(list.Get(1));
        list.RemoveEnd();
        System.out.println("Size: " + list.GetSize());
        list.Remove(1);
        System.out.println("Size: " + list.GetSize());
        System.out.println(list.Get(1));

        list.Add(15);
        list.Swap(0, 1);
        System.out.println("Size: " + list.GetSize());
        System.out.println(list.Get(1));

    }

}

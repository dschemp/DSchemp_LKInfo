package liste;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Liste {
    public ListElement head;

    public Liste() { }

    public Liste(int[] array) {
        for (int x : array) {
            this.add(x);
        }
    }

    public void add(int content) {
        if (head == null)
            head = new ListElement(content, null, null);
        else {
            ListElement lastElement = FindLastElement();
            lastElement.NextElement = new ListElement(content, null, lastElement);
        }
    }
    public void add(int content, int index) {
        ListElement indexElement = GetElement(index);
        ListElement lastIndexElement = indexElement.LastElement;

        ListElement insertElement = new ListElement(content, indexElement, lastIndexElement);

        lastIndexElement.NextElement = insertElement;
        indexElement.LastElement = insertElement;
        insertElement.LastElement = lastIndexElement;
        insertElement.NextElement = indexElement;
    }

    public void remove(int index) {
        if (index >= this.getSize())
            throw new IndexOutOfBoundsException();

        ListElement deleteElement = GetElement(index);
        ListElement lastDeleteElement = deleteElement.LastElement;
        ListElement nextDeleteElement = deleteElement.NextElement;

        // TODO: NullPointerException
        lastDeleteElement.NextElement = nextDeleteElement;
        nextDeleteElement.LastElement = lastDeleteElement;

        // TODO: NullPointerException
        lastDeleteElement.NextElement = nextDeleteElement;
        nextDeleteElement.LastElement = lastDeleteElement;
    }

    public void remove() {
        ListElement lastElement = FindLastElement();
        lastElement.LastElement.NextElement = null;
    }

    public int getAtIndex(int index) {
        if (index >= this.getSize())
            throw new IndexOutOfBoundsException();

        ListElement end = head;
        int i = 0;
        while (i != index) {
            end = end.NextElement;
            i++;
        }
        return end.Content;
    }

    public void swap(int index_one, int index_two) {
        int listSize = this.getSize();
        if (index_one >= listSize || index_two >= listSize)
            throw new IndexOutOfBoundsException();

        int content_one = getAtIndex(index_one);
        int content_two = getAtIndex(index_two);

        this.GetElement(index_one).Content = content_two;
        this.GetElement(index_two).Content = content_one;
    }

    public void sort() {
        throw new NotImplementedException();
    }

    public int getSize() {
        ListElement end = head;

        int length = 0;
        while (end != null) {
            end = end.NextElement;
            length++;
        }

        return length;
    }

    public int[] toArray() {
        int[] array = new int[this.getSize()];
        for (int i = 0; i < array.length; i++)
            array[i] = this.getAtIndex(i);
        return array;
    }

    private ListElement FindLastElement() {
        ListElement end = head;
        while (true) {
            if (end == null)
                return null;
            else if (end.NextElement == null)
                return end;
            end = end.NextElement;
        }
    }

    private ListElement GetElement(int index) {
        if (index >= this.getSize())
            throw new IndexOutOfBoundsException();

        ListElement end = head;
        int i = 0;
        while (i != index) {
            end = end.NextElement;
            i++;
        }
        return end;
    }

}

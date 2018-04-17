package liste;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Liste {

    private ListElement head;

    private ListElement FindLastElement() {
        ListElement end = head;
        while (end.nextElement != null) {
            end = end.nextElement;
        }
        return end;
    }

    private ListElement FindBeforeLastElement() {
        ListElement end = head;
        while(end.nextElement.nextElement != null) {
            end = end.nextElement;
        }
        return end;
    }

    public void AddToEnd(int content) {
        ListElement element = new ListElement(content, null);

        if (head == null)
            head = element;
        else {
            ListElement end = FindLastElement();
            end.nextElement = element;
        }
    }

    public void RemoveEnd() {
        int size = GetSize();
        switch (size) {
            case 0:
                return;
            case 1:
                head = null;
                break;
            default:
                ListElement beforeLast = FindBeforeLastElement();
                beforeLast.nextElement = null;
                break;
        }
    }

    public void Insert(int content, int index) {
        throw new NotImplementedException();
    }

    public int Get(int index) {
        if (index >= GetSize())
            throw new IndexOutOfBoundsException();
        else {
            int length = 0;
            ListElement end = head;
            while (length != index) {
                end = end.nextElement;
            }
            return end.content;
        }
    }

    public void Sort() {
        throw new NotImplementedException();
    }

    public int GetSize() {
        ListElement end = head;

        int length = 0;
        while (end != null) {
            end = end.nextElement;
            length++;
        }

        return length;
    }

}

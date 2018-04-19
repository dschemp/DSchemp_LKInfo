package liste;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.w3c.dom.html.HTMLIsIndexElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Liste {
    public ListElement head;

    public void AddToEnd(int content) {
        if (head == null)
            head = new ListElement(content, null, null);
        else {
            ListElement lastElement = FindLastElement();
            lastElement.NextElement = new ListElement(content, null, lastElement);
        }
    }

    public void RemoveEnd() {
        ListElement lastElement = FindLastElement();
        lastElement.LastElement.NextElement = null;
    }

    public void Insert(int content, int index) {
        ListElement indexElement = GetElement(index);
        ListElement lastIndexElement = indexElement.LastElement;

        ListElement insertElement = new ListElement(content, indexElement, lastIndexElement);

        lastIndexElement.NextElement = insertElement;
        indexElement.LastElement = insertElement;
        insertElement.LastElement = lastIndexElement;
        insertElement.NextElement = indexElement;
    }

    public int Get(int index) {
        if (index >= GetSize())
            throw new IndexOutOfBoundsException();

        ListElement end = head;
        int i = 0;
        while (i != index) {
            end = end.NextElement;
            i++;
        }
        return end.Content;
    }

    public void Sort() {
        throw new NotImplementedException();
    }

    public int GetSize() {
        ListElement end = head;

        int length = 0;
        while (end != null) {
            end = end.NextElement;
            length++;
        }

        return length;
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
        if (index >= GetSize())
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

package liste;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.w3c.dom.html.HTMLIsIndexElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Liste {
    public ListElement head;

    public void Add(int content) {
        if (head == null)
            head = new ListElement(content, null, null);
        else {
            ListElement lastElement = FindLastElement();
            lastElement.NextElement = new ListElement(content, null, lastElement);
        }
    }

    public void Remove(int index) {
        if (index >= GetSize())
            throw new IndexOutOfBoundsException();

        ListElement deleteElement = GetElement(index);
        ListElement lastDeleteElement = deleteElement.LastElement;
        ListElement nextDeleteElement = deleteElement.NextElement;

        // TODO: NullPointerException
        lastDeleteElement.NextElement = nextDeleteElement;
        nextDeleteElement.LastElement = lastDeleteElement;

        lastDeleteElement.NextElement = nextDeleteElement;
        nextDeleteElement.LastElement = lastDeleteElement;
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

    public void Swap(int index_one, int index_two) {
        int listSize = GetSize();
        if (index_one >= listSize || index_two >= listSize)
            throw new IndexOutOfBoundsException();

        int content_one = Get(index_one);
        int content_two = Get(index_two);

        this.Remove(index_one);
        this.Remove(index_two);

        this.Insert(content_one, index_two);
        this.Insert(content_two, index_one);
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

package liste;

/**
 * Liste mit doppelter Verknüpfung
 */
public class Liste {
    public ListElement head;

    /**
     * Leere Liste initialisieren
     */
    public Liste() { }

    /**
     * Initalisiere eine Liste mit Inhalt aus einem Array
     */
    public Liste(int... array) {
        for (int x : array) {
            this.add(x);
        }
    }

    /**
     * Fügt ein Element am Ende der Liste ein
     * @param content
     */
    public void add(int content) {
        if (head == null)
            head = new ListElement(content, null, null);
        else {
            ListElement lastElement = FindLastElement();
            lastElement.NextElement = new ListElement(content, null, lastElement);
        }
    }

    /**
     * Fügt ein Element an eine bestimmte Selle in der Liste ein
     * @param index
     */
    public void add(int content, int index) {
        ListElement indexElement = GetElement(index);
        ListElement lastIndexElement = indexElement.LastElement;

        ListElement insertElement = new ListElement(content, indexElement, lastIndexElement);

        lastIndexElement.NextElement = insertElement;
        indexElement.LastElement = insertElement;
        insertElement.LastElement = lastIndexElement;
        insertElement.NextElement = indexElement;
    }

    /**
     * Entfernt das Element an der Stelle 'index'
     * @param index Index des zu entfernenden Elementes
     */
    public void remove(int index) {
        if (index >= this.getSize())
            throw new IndexOutOfBoundsException();

        ListElement deleteElement = GetElement(index);
        ListElement lastDeleteElement = deleteElement.LastElement;
        ListElement nextDeleteElement = deleteElement.NextElement;

        lastDeleteElement.NextElement = nextDeleteElement;
        nextDeleteElement.LastElement = lastDeleteElement;

        lastDeleteElement.NextElement = nextDeleteElement;
        nextDeleteElement.LastElement = lastDeleteElement;
    }

    /**
     * Entfernt das letzte Element der Liste
     */
    public void remove() {
        ListElement lastElement = FindLastElement();
        lastElement.LastElement.NextElement = null;
    }

    /**
     * Ruft den Inhalt in der Liste an der Stelle index ab
     * @return Inhalt an der Stelle
     */
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

    /**
     * Tauscht den Inhalt zweier Elemente miteinander
     */
    public void swap(int index_one, int index_two) {
        int listSize = this.getSize();
        if (index_one >= listSize || index_two >= listSize)
            throw new IndexOutOfBoundsException();

        int content_one = getAtIndex(index_one);
        int content_two = getAtIndex(index_two);

        this.GetElement(index_one).Content = content_two;
        this.GetElement(index_two).Content = content_one;
    }

    /**
     * Sortiert die Liste per Insertionsort
     */
    public void sort() {
        for (int x = 1; x < this.getSize(); x++) {
            for (int y = x; y > 0; y--) {
                if (this.getAtIndex(y-1) > this.getAtIndex(y)) {
                    this.swap(y, y-1);
                }
            }
        }
    }

    /**
     * Abfrage nach der Anzahl der Elemente in der Liste
     * @return Anzahl
     */
    public int getSize() {
        ListElement end = head;

        int length = 0;
        while (end != null) {
            end = end.NextElement;
            length++;
        }

        return length;
    }

    /**
     * Gibt die Liste als Array aus
     * @return List als int Array
     */
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

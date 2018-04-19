package liste;

public class ListElement {

    public int Content;
    public ListElement NextElement;
    public ListElement LastElement;

    public ListElement(int _content, ListElement _nextElement, ListElement _lastElement) {
        this.Content = _content;
        this.NextElement = _nextElement;
        this.LastElement = _lastElement;
    }

}

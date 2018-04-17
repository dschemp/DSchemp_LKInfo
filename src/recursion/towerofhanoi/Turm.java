package recursion.towerofhanoi;

import java.util.Stack;

public class Turm {

    public Stack<String> scheiben;
    public char TurmName;

    public Turm(char TurmName) {
        this.TurmName = TurmName;
        scheiben = new Stack();
    }
}

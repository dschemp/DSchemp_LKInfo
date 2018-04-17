package recursion.pythagorasbaum;

import recursion.sierpinskidreieck.SierpinskiCanvas;

import javax.swing.*;
import java.awt.*;

public class PythagorasBaum_Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pythagoras Baum");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = new Dimension(1250, 900);
        PythagorasBaumCanvas pBaum = new PythagorasBaumCanvas(new Point(500, 850), new Point(700, 850), 8);

        frame.setMinimumSize(d);
        frame.setPreferredSize(d);
        frame.add(pBaum);
        frame.setVisible(true);
    }

}

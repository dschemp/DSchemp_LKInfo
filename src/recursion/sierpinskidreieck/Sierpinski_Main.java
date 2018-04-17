package recursion.sierpinskidreieck;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Sierpinski_Main {

    public static void main(String args[]) throws IOException {
        JFrame frame = new JFrame("Sierpinski Dreieck");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = new Dimension(1001, 1000);
        SierpinskiCanvas sCanvas = new SierpinskiCanvas(7, d);

        frame.setMinimumSize(d);
        frame.setPreferredSize(d);
        frame.add(sCanvas);
        frame.setVisible(true);

        // sCanvas.createPictureFilePNG("test");
    }

}

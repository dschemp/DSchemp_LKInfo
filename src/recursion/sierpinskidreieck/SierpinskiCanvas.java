package recursion.sierpinskidreieck;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SierpinskiCanvas extends JPanel {

    private Point a1, b1, c1, a2, b2, c2, a3, b3, c3;

    private int level;
    private Dimension dim;

    public SierpinskiCanvas(int levels, Dimension size) {
        this.level = levels;
        this.dim = size;
    }

    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);
        int[] startX = {0, width, width / 2};
        int[] startY = {height - 1, height - 1, 0};
        g.drawPolygon(startX, startY, 3);

        drawTriangle(
                g,
                new Point(0, height - 1),
                new Point(width, height - 1),
                new Point(width / 2, 0),
                level);
    }

    private void drawTriangle(Graphics g, Point a, Point b, Point c, int level) {

        if (level == 0) return;

        level -= 1;

        // In das übergebene Dreieck, wird ein auf dem Kopf stehendes Dreieck eingefügt
        int xCoords[] = { c.x, (c.x + b.x) / 2, (a.x + c.x) / 2 };
        int yCoords[] = { b.y, (c.y + a.y) / 2, (c.y + a.y) / 2 };

        g.drawPolygon(xCoords, yCoords, 3);

        // 3 neue Dreiecke bestimmen
        // Dreieck unten links
        a1 = a;
        b1 = new Point(c.x, b.y);
        c1 = new Point((a.x + c.x) / 2, (c.y + a.y) / 2);
        drawTriangle(g, a1, b1, c1, level);

        // Dreieck unten rechts
        a2 = new Point(c.x, b.y);
        b2 = b;
        c2 = new Point((c.x + b.x) / 2, (c.y + a.y) / 2);
        drawTriangle(g, a2, b2, c2, level);

        // Dreieck oben mitte
        a3 = new Point((a.x + c.x) / 2, (c.y + a.y) / 2);
        b3 = new Point((c.x + b.x) / 2, (c.y + a.y) / 2);
        c3 = c;
        drawTriangle(g, a3, b3, c3, level);
    }

    public BufferedImage createBufferedImage() {
        int w = getWidth();
        int h = getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        paint(g);
        return bi;
    }

    public void createPictureFilePNG(String fileName) throws IOException {
        BufferedImage bi = createBufferedImage();
        File outfile = new File(fileName + ".png");
        ImageIO.write(bi,"png", outfile);
    }
}

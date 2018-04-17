package recursion.pythagorasbaum;

import javax.swing.*;
import java.awt.*;

public class PythagorasBaumCanvas extends JPanel {

    private Point startLowerLeft, startLowerRight;
    private int level;

    public PythagorasBaumCanvas(Point startLowerLeft, Point startLowerRight, int level) {
        this.startLowerLeft = startLowerLeft;
        this.startLowerRight = startLowerRight;
        this.level = level;
    }

    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);

        drawTree(g, startLowerLeft, startLowerRight, level);
    }

    private void drawTree(Graphics g, Point lowerLeft, Point lowerRight, int levels) {

        if (levels == 0) return;
        levels -= 1;

        // Offset bestimmen (Drehung)
        int dx = lowerRight.x - lowerLeft.x; // Offset auf X-Achse
        int dy = lowerLeft.y - lowerRight.y; // Offset auf Y-Achse

        // Quadrat zeichnen
        // uL --- uR
        // |       |
        // |       |
        // lL --- lR
        Point upperRight = new Point(lowerRight.x - dy, lowerRight.y - dx);
        Point upperLeft = new Point(lowerLeft.x - dy, lowerLeft.y - dx);

        Polygon quadrat = new Polygon();
        quadrat.addPoint(lowerLeft.x,  lowerLeft.y);
        quadrat.addPoint(lowerRight.x, lowerRight.y);
        quadrat.addPoint(upperRight.x, upperRight.y);
        quadrat.addPoint(upperLeft.x,  upperLeft.y);

        Color ursprungsFarbe = g.getColor();
        g.setColor(Color.gray);
        g.fillPolygon(quadrat);
        g.setColor(Color.white);
        g.drawPolygon(quadrat);
        g.setColor(ursprungsFarbe);

        // Dreieck zeichnen
        //    tip
        //   /   \
        //  /     \
        // uL ---- uR
        Point tipTriangle = new Point(
                (upperRight.x + upperLeft.x)/2 - (dy/2),
                (upperRight.y + upperLeft.y)/2 - (dx/2)
        );
        Polygon dreieck = new Polygon();
        dreieck.addPoint(tipTriangle.x, tipTriangle.y);
        dreieck.addPoint(upperRight.x, upperRight.y);
        dreieck.addPoint(upperLeft.x, upperLeft.y);

        Color ursprungsFarbe2 = g.getColor();
        g.setColor(Color.orange);
        g.fillPolygon(dreieck);
        g.setColor(Color.white);
        g.drawPolygon(dreieck);
        g.setColor(ursprungsFarbe2);

        // Zweig nach Links vom Dreieck
        drawTree(g, upperLeft, tipTriangle, levels);

        // Zweig nach Rechts vom Dreieck
        drawTree(g, tipTriangle, upperRight, levels);
    }
}

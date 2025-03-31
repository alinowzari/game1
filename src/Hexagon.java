import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;  // If adding rotation
import java.awt.BasicStroke;          // If customizing the stroke further
import java.awt.RenderingHints;       // If adding more rendering quality controls
public class Hexagon {
    private final int radius;
    private final Color color;

    public Hexagon(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void draw(Graphics2D g2d, Point center) {
        Polygon hexagon = new Polygon();

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            int x = (int) (center.x + radius * Math.cos(angle));
            int y = (int) (center.y + radius * Math.sin(angle));
            hexagon.addPoint(x, y);
        }

        g2d.setColor(color);
        g2d.fill(hexagon);
        g2d.setColor(color.darker());
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(hexagon);
    }
}
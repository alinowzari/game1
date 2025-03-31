import java.awt.*;
import java.awt.geom.Path2D;

public class Triangle {
    private double angle;            // Current angle around the hexagon
    private final double orbitRadius; // Distance from center to triangle base
    private final double size;       // Size of the triangle
    private final double step;       // 60 degrees per movement (hexagon side)
    private final Color color;

    private static final double TRIANGLE_HEIGHT_RATIO = 0.866; // sqrt(3)/2 for equilateral

    public Triangle(double hexRadius, double size, double gap, Color color) {
        this.orbitRadius = hexRadius + gap + (size * TRIANGLE_HEIGHT_RATIO);
        this.size = size;
        this.color = color;
        this.angle = Math.PI / 6;    // ✅ Start at middle of hexagon side (30°)
        this.step = Math.PI / 3;     // 60 degrees per hexagon side
    }

    public void moveLeft() {
        angle -= step;
        angle = normalizeAngle(angle);
    }

    public void moveRight() {
        angle += step;
        angle = normalizeAngle(angle);
    }

    private double normalizeAngle(double angle) {
        return (angle + 2 * Math.PI) % (2 * Math.PI);  // Keep angle within 0 - 360°
    }

    public void draw(Graphics2D g2d, Point center) {
        // 🎯 Calculate base center position
        double baseCenterX = center.x + orbitRadius * Math.cos(angle);
        double baseCenterY = center.y + orbitRadius * Math.sin(angle);

        // 🎯 Create equilateral triangle
        Path2D triangle = new Path2D.Double();

        for (int i = 0; i < 3; i++) {
            double theta = angle + i * (2 * Math.PI / 3);  // 120° between vertices
            double x = baseCenterX + size * Math.cos(theta);
            double y = baseCenterY + size * Math.sin(theta);

            if (i == 0) {
                triangle.moveTo(x, y);
            } else {
                triangle.lineTo(x, y);
            }
        }

        triangle.closePath();

        // ✅ Draw the triangle
        g2d.setColor(color);
        g2d.fill(triangle);
        g2d.setColor(color.darker());
        g2d.draw(triangle);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    private Timer timer;       // Game loop timer
    private int hexagonSize;   // Size of the hexagon
    private Color hexagonColor; // Hexagon color

    public GamePanel() {
        setBackground(Color.BLACK);
        hexagonColor = Color.CYAN;  // Initial color
        hexagonSize = 75;          // Initial size of the hexagon

        // ✅ Timer for game loop (refreshing the screen)
        timer = new Timer(16, this);  // 60 FPS (~16ms per frame)
        timer.start();
    }

    // ✅ Game rendering method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the hexagon
        drawHexagon(g2d);
    }

    // ✅ Method to draw a centered hexagon
    private void drawHexagon(Graphics2D g2d) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Create a polygon for the hexagon
        Polygon hexagon = new Polygon();

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            int x = (int) (centerX + hexagonSize * Math.cos(angle));
            int y = (int) (centerY + hexagonSize * Math.sin(angle));
            hexagon.addPoint(x, y);
        }

        // Fill and outline the hexagon
        g2d.setColor(hexagonColor);
        g2d.fill(hexagon);
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(hexagon);
    }

    // ✅ Game loop refresh
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    // ✅ Method to start the game
    public void start() {
        timer.start();
    }

    // ✅ Method to stop the game
    public void stop() {
        timer.stop();
    }
}

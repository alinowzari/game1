////import javax.swing.*;
////import java.awt.*;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////
////public class GamePanel extends JPanel implements ActionListener {
////
////    private Timer timer;       // Game loop timer
////    private int hexagonSize;   // Size of the hexagon
////    private Color hexagonColor; // Hexagon color
////
////    public GamePanel() {
////        setBackground(Color.BLACK);
////        hexagonColor = Color.CYAN;  // Initial color
////        hexagonSize = 75;          // Initial size of the hexagon
////
////        // ✅ Timer for game loop (refreshing the screen)
////        timer = new Timer(16, this);  // 60 FPS (~16ms per frame)
////        timer.start();
////    }
////
////    // ✅ Game rendering method
////    @Override
////    protected void paintComponent(Graphics g) {
////        super.paintComponent(g);
////
////        Graphics2D g2d = (Graphics2D) g;
////        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
////
////        // Draw the hexagon
////        drawHexagon(g2d);
////    }
////
////    // ✅ Method to draw a centered hexagon
////    private void drawHexagon(Graphics2D g2d) {
////        int centerX = getWidth() / 2;
////        int centerY = getHeight() / 2;
////
////        // Create a polygon for the hexagon
////        Polygon hexagon = new Polygon();
////
////        for (int i = 0; i < 6; i++) {
////            double angle = Math.toRadians(60 * i);
////            int x = (int) (centerX + hexagonSize * Math.cos(angle));
////            int y = (int) (centerY + hexagonSize * Math.sin(angle));
////            hexagon.addPoint(x, y);
////        }
////
////        // Fill and outline the hexagon
////        g2d.setColor(hexagonColor);
////        g2d.fill(hexagon);
////        g2d.setColor(Color.BLUE);
////        g2d.setStroke(new BasicStroke(3));
////        g2d.draw(hexagon);
////    }
////
////    // ✅ Game loop refresh
////    @Override
////    public void actionPerformed(ActionEvent e) {
////        repaint();
////    }
////
////    // ✅ Method to start the game
////    public void start() {
////        timer.start();
////    }
////
////    // ✅ Method to stop the game
////    public void stop() {
////        timer.stop();
////    }
////}
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//
//public class GamePanel extends JPanel implements ActionListener {
//
//    private Timer timer;            // Game loop timer
//    private int hexagonSize;        // Size of the hexagon
//    private Color hexagonColor;     // Hexagon color
//    private Triangle triangle;      // Triangle orbiting the hexagon
//    private final int gap = 40;     // Gap between triangle and hexagon
//
//    public GamePanel() {
//        setBackground(Color.BLACK);
//        hexagonColor = Color.CYAN;   // Initial color
//        hexagonSize = 100;           // Size of the hexagon
//
//        // ✅ Create the triangle orbiting the hexagon
//        triangle = new Triangle(hexagonSize, 30, gap);
//
//        // ✅ Timer for game loop (refreshing the screen)
//        timer = new Timer(16, this);  // ~60 FPS
//        timer.start();
//
//        // ✅ Keyboard controls for triangle movement
//        setFocusable(true);
//        requestFocusInWindow();
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    triangle.moveLeft();   // Move counterclockwise
//                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    triangle.moveRight();  // Move clockwise
//                }
//                repaint();
//            }
//        });
//    }
//
//    // ✅ Rendering the hexagon and triangle
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // ✅ Draw hexagon
//        drawHexagon(g2d);
//
//        // ✅ Draw triangle around the hexagon
//        triangle.draw(g2d, getWidth() / 2, getHeight() / 2);
//    }
//
//    // ✅ Method to draw the centered hexagon
//    private void drawHexagon(Graphics2D g2d) {
//        int centerX = getWidth() / 2;
//        int centerY = getHeight() / 2;
//
//        Polygon hexagon = new Polygon();
//
//        for (int i = 0; i < 6; i++) {
//            double angle = Math.toRadians(60 * i);
//            int x = (int) (centerX + hexagonSize * Math.cos(angle));
//            int y = (int) (centerY + hexagonSize * Math.sin(angle));
//            hexagon.addPoint(x, y);
//        }
//
//        // ✅ Fill and outline the hexagon
//        g2d.setColor(hexagonColor);
//        g2d.fill(hexagon);
//        g2d.setColor(Color.BLUE);
//        g2d.setStroke(new BasicStroke(3));
//        g2d.draw(hexagon);
//    }
//
//    // ✅ Game loop refresh
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }
//
//    // ✅ Start the game loop
//    public void start() {
//        timer.start();
//    }
//
//    // ✅ Stop the game loop
//    public void stop() {
//        timer.stop();
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private final Hexagon hexagon;
    private final Triangle triangle;
    private final Timer gameLoop;

    public GamePanel() {
        hexagon = new Hexagon(75, Color.CYAN);
        triangle = new Triangle(hexagon.getRadius(), 20, 10, Color.RED);

        setBackground(Color.BLACK);
        setFocusable(true);  // ✅ Ensures panel receives key inputs
        requestFocusInWindow();  // ✅ Requests focus for key events
        setupControls();

        gameLoop = new Timer(16, e -> repaint());
        gameLoop.start();
    }

    private void setupControls() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> triangle.moveLeft();
                    case KeyEvent.VK_RIGHT -> triangle.moveRight();
                }
                repaint(); // ✅ Ensure UI updates immediately on key press
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Point center = new Point(getWidth() / 2, getHeight() / 2);
        hexagon.draw(g2d, center);
        triangle.draw(g2d, center);
    }
}

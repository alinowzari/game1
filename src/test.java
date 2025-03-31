import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    public test() {
        setTitle("Triangle Example");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ✅ Create a panel to hold the triangle
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // ✅ Draw the triangle
//                Triangle triangle = new Triangle(75, 20, 5);
//                triangle.draw(g2d, 250, 200);
            }
        };

        panel.setBackground(Color.BLACK);   // Set panel background color
        add(panel);                         // ✅ Add the panel to the frame

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(test::new);
    }
}

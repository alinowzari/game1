import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    public test() {
        setTitle("GridBagLayout Example");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setting GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Button 1 - Top Left
        JButton btn1 = new JButton("Button 1");
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 0;  // Row 0
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding
        gbc.fill = GridBagConstraints.BOTH;  // Stretch both horizontally & vertically
        gbc.weightx = 0.5;  // Stretch horizontally
        gbc.weighty = 0.5;  // Stretch vertically
        add(btn1, gbc);

        // Button 2 - Top Right
        JButton btn2 = new JButton("Button 2");
        gbc.gridx = 1;  // Column 1
        gbc.gridy = 0;  // Row 0
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(btn2, gbc);

        // Button 3 - Bottom Left
        JButton btn3 = new JButton("Button 3");
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 1;  // Row 1
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(btn3, gbc);

        // Button 4 - Bottom Right
        JButton btn4 = new JButton("Button 4");
        gbc.gridx = 1;  // Column 1
        gbc.gridy = 1;  // Row 1
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(btn4, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(test::new);
    }
}

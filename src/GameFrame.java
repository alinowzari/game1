import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Hexagon Triangle Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel);

        setVisible(true);
    }
}

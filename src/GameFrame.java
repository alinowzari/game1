import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("Super Hexagon Gameplay");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // ✅ Add the GamePanel
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);
        gamePanel.start();
    }
}
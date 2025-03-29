import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameMenu extends JFrame {
    private MusicPlayer musicPlayer;
    public HashMap<String, String> players;
    private boolean saveHistory = true;  // ✅ Flag to track if history should be saved

    public GameMenu(HashMap<String, String> players) {
        this.players = players;

        setTitle("Super Hexagon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();

        // ✅ Initialize Music Player
        musicPlayer = new MusicPlayer("background.wav");
        musicPlayer.play();

        setVisible(true);
    }

    public void initMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // ✅ Title Label
        JLabel title = new JLabel("Super Hexagon");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPanel.add(title, gbc);

        // ✅ Start Game Button
        JButton startButton = new JButton("Start Game");
        styleButton(startButton);
        startButton.addActionListener(e -> openStartGameMenu());
        gbc.gridy = 1;
        menuPanel.add(startButton, gbc);

        // ✅ Settings Button
        JButton settingsButton = new JButton("Settings");
        styleButton(settingsButton);
        settingsButton.addActionListener(e -> openSettings());
        gbc.gridy = 2;
        menuPanel.add(settingsButton, gbc);

        // ✅ Exit Button
        JButton exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridy = 3;
        menuPanel.add(exitButton, gbc);

        add(menuPanel);
    }

    // ✅ Save history setter method
    public void setSaveHistory(boolean saveHistory) {
        this.saveHistory = saveHistory;
    }

    public boolean isSaveHistory() {
        return saveHistory;
    }

    private void openSettings() {
        this.setVisible(false);
        new Settings(this, musicPlayer, saveHistory);
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.RED);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void openStartGameMenu() {
        this.setVisible(false);
        new StartGameMenu(this, players, saveHistory);
    }
}

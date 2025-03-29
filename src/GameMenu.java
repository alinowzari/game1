import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {
    private MusicPlayer musicPlayer;
    public GameMenu() {
        setTitle("Super Hexagon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        initMenu();
        musicPlayer = new MusicPlayer("background.wav");
        String musicFilePath="background.wav";
        System.out.println("Loading music from: " + musicFilePath);
// Path to the audio file
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
        this.styleButton(startButton);
        gbc.gridy = 1;
        menuPanel.add(startButton, gbc);

        // ✅ Settings Button - Switch to Settings Frame
        JButton settingsButton = new JButton("Settings");
        this.styleButton(settingsButton);
        settingsButton.addActionListener(e -> openSettings());  // 🔥 Switch to settings
        gbc.gridy = 2;
        menuPanel.add(settingsButton, gbc);

        // ✅ Exit Button
        JButton exitButton = new JButton("Exit");
        this.styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridy = 3;
        menuPanel.add(exitButton, gbc);

        add(menuPanel);
    }

    // ✅ Open settings with a reference to the current menu
    private void openSettings() {
        this.setVisible(false);         // 🔥 Hide the menu
        new Settings(this, musicPlayer);             // ✅ Pass this instance to the settings
    }

    // 🔥 Button styling method
    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.RED);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }

    // ✅ Main method to run the game menu
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMenu::new);
    }
}


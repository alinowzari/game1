import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMenu extends JFrame {
    private static final String GAME_TITLE = "Super Hexagon";
    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);
    private static final Dimension BUTTON_SIZE = new Dimension(200, 50);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 40);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 18);

    private final MusicPlayer musicPlayer;
    private final HashMap<String, PlayerStats> players; // Maintain original HashMap structure
    private boolean saveHistory = true;
    private boolean musicEnabled = true;

    public GameMenu(HashMap<String, PlayerStats> players) {
        this.players = players;
        this.musicPlayer = initializeMusicPlayer(); // Ensure this is initialized

        setupWindow();
        initMenu();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cleanupResources();
            }
        });
    }


    private MusicPlayer initializeMusicPlayer() {
        try {
            MusicPlayer player = new MusicPlayer("background.wav"); // Initialize here
            if (musicEnabled) {
                player.play(true); // Loop the music
            }
            return player;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Could not load background music: " + e.getMessage(),
                    "Audio Error",
                    JOptionPane.WARNING_MESSAGE);
            return null; // Return null if error occurs
        }
    }

    private void setupWindow() {
        setTitle(GAME_TITLE);
        setSize(WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initMenu() {
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));

        // Title Label
        JLabel title = new JLabel(GAME_TITLE, SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(Color.WHITE);
        menuPanel.add(title, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        buttonPanel.add(createMenuButton("Start Game", e -> openStartGameMenu()));
        buttonPanel.add(createMenuButton("Settings", e -> openSettings()));
        buttonPanel.add(createMenuButton("Exit", e -> System.exit(0)));

        menuPanel.add(buttonPanel, BorderLayout.CENTER);
        add(menuPanel);
    }

    private JButton createMenuButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setPreferredSize(BUTTON_SIZE);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.RED);
        button.setFocusPainted(false);
        button.setFont(BUTTON_FONT);
        button.addActionListener(action);
        return button;
    }

    private void openSettings() {
        // Hide the main menu and show the settings window
        this.setVisible(false); // Hide GameMenu
        new Settings(this, musicPlayer, saveHistory).setVisible(true); // Show Settings
    }

    private void openStartGameMenu() {
        setVisible(false);
        new StartGameMenu(this, players, saveHistory).setVisible(true);
    }

    public void setSaveHistory(boolean saveHistory) {
        this.saveHistory = saveHistory;
    }

    public boolean isSaveHistory() {
        return saveHistory;
    }

    public void setMusicEnabled(boolean enabled) {
        this.musicEnabled = enabled;
        if (musicPlayer != null) {
            if (enabled) {
                musicPlayer.play(true);
            } else {
                musicPlayer.stop();
            }
        }
    }

    private void cleanupResources() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            musicPlayer.close();
        }
    }
}
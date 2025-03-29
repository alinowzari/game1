import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {

    private GameMenu mainMenu;
    private MusicPlayer musicPlayer;  // ✅ Reference to the music player

    private JSlider volumeSlider;
    private JCheckBox saveHistoryCheckBox;
    private boolean saveHistory = true;

    public Settings(GameMenu mainMenu, MusicPlayer musicPlayer) {
        this.mainMenu = mainMenu;
        this.musicPlayer = musicPlayer;  // ✅ Store the music player
        setTitle("Settings");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        initSettings();
        setVisible(true);
    }

    private void initSettings() {
        JPanel settingsPanel = new JPanel(new GridBagLayout());
        settingsPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel volumeLabel = createLabel("Volume:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        settingsPanel.add(volumeLabel, gbc);

        // Create the volume slider (0-100 range)
        volumeSlider = new JSlider(0, 100, 50);  // Default volume set to 50%
        volumeSlider.setBackground(Color.BLACK);
        volumeSlider.setForeground(Color.WHITE);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        // Add a change listener to adjust the volume
        volumeSlider.addChangeListener(e -> {
            int sliderValue = volumeSlider.getValue();  // Get the current slider value
            float volume = sliderValue / 100.0f;  // Convert to range 0.0 - 1.0
            musicPlayer.setVolume(volume);  // Update the volume in MusicPlayer
        });

        gbc.gridx = 1;
        settingsPanel.add(volumeSlider, gbc);

        // Checkbox for save history
        saveHistoryCheckBox = new JCheckBox("Save Player History", true);
        saveHistoryCheckBox.setBackground(Color.BLACK);
        saveHistoryCheckBox.setForeground(Color.WHITE);
        saveHistoryCheckBox.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        settingsPanel.add(saveHistoryCheckBox, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);

        JButton applyButton = new JButton("Apply");
        styleButton(applyButton);
        applyButton.addActionListener(e -> applySettings());

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> returnToMenu());

        buttonPanel.add(applyButton);
        buttonPanel.add(backButton);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        settingsPanel.add(buttonPanel, gbc);

        add(settingsPanel);
    }

    private void returnToMenu() {
        dispose();
        mainMenu.setVisible(true);
        musicPlayer.play();  // ✅ Resume music when returning
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        return label;
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.RED);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void applySettings() {
        saveHistory = saveHistoryCheckBox.isSelected();
        JOptionPane.showMessageDialog(this, "Settings Applied!");
    }
}

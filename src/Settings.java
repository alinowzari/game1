import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {

    private GameMenu mainMenu;
    private MusicPlayer musicPlayer;       // ✅ Reference to the music player
    private JSlider volumeSlider;
    private JCheckBox saveHistoryCheckBox;

    // ✅ Reference to the global saveHistory flag
    private boolean saveHistory;

    public Settings(GameMenu mainMenu, MusicPlayer musicPlayer, boolean saveHistory) {
        this.mainMenu = mainMenu;
        this.musicPlayer = musicPlayer;
        this.saveHistory = saveHistory;    // ✅ Initialize with current setting

        setTitle("Settings");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initSettings();
        setVisible(true);
    }

    private void initSettings() {
        JPanel settingsPanel = new JPanel(new GridBagLayout());
        settingsPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ✅ Volume label
        JLabel volumeLabel = createLabel("Volume:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        settingsPanel.add(volumeLabel, gbc);

        // ✅ Volume slider
        volumeSlider = new JSlider(0, 100, (int) (musicPlayer.getVolume() * 100));  // Get current volume
        volumeSlider.setBackground(Color.BLACK);
        volumeSlider.setForeground(Color.WHITE);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        volumeSlider.addChangeListener(e -> {
            int sliderValue = volumeSlider.getValue();
            float volume = sliderValue / 100.0f;
            musicPlayer.setVolume(volume);
        });

        gbc.gridx = 1;
        settingsPanel.add(volumeSlider, gbc);

        // ✅ Save history checkbox
        saveHistoryCheckBox = new JCheckBox("Save Player History", saveHistory);
        saveHistoryCheckBox.setBackground(Color.BLACK);
        saveHistoryCheckBox.setForeground(Color.WHITE);
        saveHistoryCheckBox.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        settingsPanel.add(saveHistoryCheckBox, gbc);

        // ✅ Button panel
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
    }

    private void applySettings() {
        // ✅ Update the global saveHistory flag
        saveHistory = saveHistoryCheckBox.isSelected();
        mainMenu.setSaveHistory(saveHistory);  // ✅ Update in GameMenu
        JOptionPane.showMessageDialog(this, "Settings Applied!");
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
}

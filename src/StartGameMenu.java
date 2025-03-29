import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class StartGameMenu extends JFrame {

    private GameMenu mainMenu;
    private JTextField nameField;                  // ✅ Text field for the player's name
    public HashMap<String, String> players;// ✅ HashMap to store player names and history
    private boolean saveHistory;

    public StartGameMenu(GameMenu mainMenu, HashMap<String, String> players,  boolean saveHistory) {
        this.mainMenu = mainMenu;
        this.players = players;
        this.saveHistory = saveHistory;
        setTitle("Start Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        initStartMenu();
        setVisible(true);
    }

    private void initStartMenu() {
        JPanel startPanel = new JPanel(new GridBagLayout());
        startPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ✅ Title Label
        JLabel titleLabel = new JLabel("Enter Your Name:");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        startPanel.add(titleLabel, gbc);

        // ✅ Name Input Field
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        startPanel.add(nameField, gbc);

        // ✅ Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);

        // 🎯 Start Button
        JButton startButton = new JButton("Start");
        styleButton(startButton);
        startButton.addActionListener(e -> startGame());

        // 🔙 Back Button
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> returnToMainMenu());

        buttonPanel.add(startButton);
        buttonPanel.add(backButton);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        startPanel.add(buttonPanel, gbc);

        add(startPanel);
    }

    private void startGame() {
        String playerName = nameField.getText().trim();

        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (saveHistory) {
            players.put(playerName, "New Game Started");
            System.out.println("Saving history for: " + playerName);
        } else {
            System.out.println("Not saving history.");
        }
        new GameFrame();
        dispose();
    }

    private void returnToMainMenu() {
        dispose();
        mainMenu.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(150, 50));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.RED);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }
}

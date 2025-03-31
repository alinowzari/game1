import javax.swing.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HashMap<String, PlayerStats> players = new HashMap<>(); // Better to use a dedicated class
            new GameMenu(players).setVisible(true);
        });
    }
}

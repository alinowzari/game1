import javax.swing.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HashMap<String, String> players = new HashMap<>();
            new GameMenu(players);
        });
    }
}

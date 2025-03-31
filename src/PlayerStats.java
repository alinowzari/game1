import java.util.ArrayList;

public class PlayerStats {
    ArrayList<Double> records=new ArrayList<>();

    public PlayerStats() {
        this.records=new ArrayList<>();
    }
    public void addRecord(double record) {
        records.add(record);
    }
}

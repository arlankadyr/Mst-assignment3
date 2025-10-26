// Main.java
import Utils.ResultRecorder;

public class Main {
    public static void main(String[] args) {
        try {
            // УБРАЛ "/" — теперь ищет прямо в classpath
            ResultRecorder.recordResults("input_small.json", "results/output_small.json");
            ResultRecorder.recordResults("input_medium.json", "results/output_medium.json");
            ResultRecorder.recordResults("input_large.json", "results/output_large.json");

            System.out.println("MST calculations completed! Check results/ folder.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
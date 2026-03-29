import java.io.*;
import java.util.*;
/**
 * MAIN CLASS UseCase12DataPersistenceRecovery
 * Use Case 12: Data Persistence & System Recovery
 * @version 12.1
 */
class PersistenceService {
    private static final String FILE_NAME = "inventory.dat";
    public Map<String, Integer> loadInventory() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No valid inventory data found. Starting fresh.");
                return null;
            }
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));
            Map<String, Integer> inventory =
                    (Map<String, Integer>) in.readObject();
            in.close();
            System.out.println("Inventory recovered successfully.");
            return inventory;
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return null;
        }
    }
    public void saveInventory(Map<String, Integer> inventory) {
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(inventory);
            out.close();
            System.out.println("Inventory saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving inventory.");
        }
    }
}
public class mystayapp {
    public static void main(String[] args) {
        System.out.println("System Recovery");
        PersistenceService service = new PersistenceService();
        Map<String, Integer> inventory = service.loadInventory();
        if (inventory == null) {
            inventory = new HashMap<>();
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
        service.saveInventory(inventory);
    }
}
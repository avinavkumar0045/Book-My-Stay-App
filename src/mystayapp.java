import java.util.*;
/**
 * MAIN CLASS UseCase10BookingCancellation
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * @version 10.1
 */
class RoomInventory {
    private Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }
    public void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }
    public int getAvailability(String roomType) {
        return inventory.get(roomType);
    }
}
class CancellationService {
    private Stack<String> rollbackStack;
    public CancellationService() {
        rollbackStack = new Stack<>();
    }
    public void cancelBooking(String reservationId, String roomType, RoomInventory inventory) {
        rollbackStack.push(reservationId);
        inventory.increaseAvailability(roomType);
        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }
    public void displayRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");
        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }
    }
}
public class mystayapp {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");
        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();
        service.cancelBooking("Single-1", "Single", inventory);
        service.displayRollbackHistory();
        System.out.println("\nUpdated Single Room Availability: "
                + inventory.getAvailability("Single"));
    }
}
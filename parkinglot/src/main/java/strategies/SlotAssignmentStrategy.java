package strategies;

import models.ParkingFloor;
import models.Ticket;

import java.util.List;

public interface SlotAssignmentStrategy {
    /**
     * Set slot against the ticket out of available slots from any of the floor's slots
     * and Vehicle type
     * @param floorList
     * @param ticket
     */
    public void reserveSlot(List<ParkingFloor> floorList, Ticket ticket);
}

package strategies;

import exceptions.ParkingLotFullException;
import models.*;

import java.util.List;

public class NearestFreeSlotAssignmentStrategy implements SlotAssignmentStrategy{

    @Override
    public void reserveSlot(List<ParkingFloor> floorList, SlotType desiredSlotType, Ticket ticket) {
        for(ParkingFloor floor: floorList) {
            for(ParkingSlot parkingSlot : floor.getSlots()) {
                if(parkingSlot.getStatus().equals(SlotStatus.FREE) &&
                       parkingSlot.getType() == desiredSlotType) {
                    ticket.setAllotedSlot(parkingSlot);
                    parkingSlot.setStatus(SlotStatus.OCCUPIED);
                    parkingSlot.setVehicle(ticket.getVehicle());
                    return;
                }
            }
        }
        throw new ParkingLotFullException("No free slots available for this vehicle type");
    }
}

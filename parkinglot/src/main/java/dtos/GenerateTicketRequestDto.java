package dtos;

import models.SlotType;
import models.Vehicle;

public class GenerateTicketRequestDto {

    private Long entryGateId;

    private Vehicle vehicle;

    private Long parkingLotId;

    private SlotType desiredSlotType;
    public Long getEntryGateId() {
        return entryGateId;
    }

    public void setEntryGateId(Long entryGateId) {
        this.entryGateId = entryGateId;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public SlotType getDesiredSlotType() {
        return desiredSlotType;
    }

    public void setDesiredSlotType(SlotType desiredSlotType) {
        this.desiredSlotType = desiredSlotType;
    }
}

package models;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor extends BaseModel {

    private String floorNumber;
    private List<ParkingSlot> parkingSlots;

    public ParkingFloor(String floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSlots = new ArrayList<>();
    }
    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSlot> getSlots() {
        return parkingSlots;
    }

    public void setSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }


}

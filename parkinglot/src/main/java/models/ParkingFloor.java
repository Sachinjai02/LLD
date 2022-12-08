package models;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor extends BaseModel {

    private String floorNumber;
    private List<Slot> slots;

    public ParkingFloor(String floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }
    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }


}

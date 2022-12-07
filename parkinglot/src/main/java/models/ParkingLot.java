package models;

import java.util.List;
import java.util.Map;

public class ParkingLot extends BaseModel{
    private List<ParkingFloor> floors;
    private List<Gate> gates;
    private String address;
    private Map<VehicleType, Long> basePrices;
    private Map<VehicleType, Double> perHourMultiplier;

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<VehicleType, Long> getBasePrices() {
        return basePrices;
    }

    public void setBasePrices(Map<VehicleType, Long> basePrices) {
        this.basePrices = basePrices;
    }

    public Map<VehicleType, Double> getPerHourMultiplier() {
        return perHourMultiplier;
    }

    public void setPerHourMultiplier(Map<VehicleType, Double> perHourMultiplier) {
        this.perHourMultiplier = perHourMultiplier;
    }
}

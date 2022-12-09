package models;

public class Vehicle extends BaseModel {
    private String vehicleNum;
    private VehicleType vehicleType;

    public Vehicle(Long id, VehicleType car, String vehicleNum) {
        this.setId(id);
        this.vehicleType = car;
        this.vehicleNum = vehicleNum;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}

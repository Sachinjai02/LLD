package dtos;

public class CreateParkingLotRequestDto {
    private String address;
    private int numberOfFloors;

    public int getNumberOfGates() {
        return numberOfGates;
    }

    public void setNumberOfGates(int numberOfGates) {
        this.numberOfGates = numberOfGates;
    }

    private int numberOfGates;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
}

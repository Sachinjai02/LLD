package dtos;

import models.ParkingLot;

public class CreateParkingLotResponseData {
    private ParkingLot parkingLot;

    public CreateParkingLotResponseData(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}

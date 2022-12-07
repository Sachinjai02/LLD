package dtos;

import models.ParkingLot;

public class CreateParkingLotResponse {
    private ParkingLot parkingLot;

    public CreateParkingLotResponse(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}

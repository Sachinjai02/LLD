package dtos;

import models.ParkingLot;

public class UpdateParkingLotResponseData {
    private ParkingLot parkingLot;

    public UpdateParkingLotResponseData(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}

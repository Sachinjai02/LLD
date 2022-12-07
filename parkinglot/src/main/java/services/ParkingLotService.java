package services;

import models.ParkingLot;
import repositories.ParkingLotRepository;

public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot createParkingLot(String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(address);
        parkingLotRepository.save(parkingLot);
        return parkingLot;
    }
}

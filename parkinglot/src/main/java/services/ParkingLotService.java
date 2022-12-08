package services;

import models.ParkingFloor;
import models.ParkingLot;
import repositories.ParkingFloorRepository;
import repositories.ParkingLotRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;
    private ParkingFloorRepository parkingFloorRepository;
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingFloorRepository parkingFloorRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingFloorRepository = parkingFloorRepository;
    }

    public ParkingLot createParkingLot(String address, int numberFloors) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(address);
        parkingLot.setFloors(getFloorList(numberFloors));
        parkingLotRepository.save(parkingLot);
        return parkingLot;
    }

    private List<ParkingFloor> getFloorList(int numberFloors) {
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        for(int i=0;i<numberFloors;++i) {
            parkingFloors.add(parkingFloorRepository.save(i+""));
        }
        return parkingFloors;
    }
}

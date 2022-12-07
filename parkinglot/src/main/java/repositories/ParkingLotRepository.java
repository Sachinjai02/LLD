package repositories;

import models.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotRepository {

    //in memory db
    private Map<Long, ParkingLot> parkingLots = new HashMap<>();
    private Long nextId = 1l;
    public ParkingLot save(ParkingLot parkingLot) {
        parkingLot.setId(nextId);
        parkingLots.put(nextId, parkingLot);
        nextId++;
        return parkingLot;
    }
}

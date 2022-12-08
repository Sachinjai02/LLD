package repositories;

import models.ParkingFloor;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloorRepository {
    private Map<Long, ParkingFloor> parkingFloorMap = new HashMap<>();
    private Long nextId = 1l;

    public ParkingFloor save(String floorNumber) {
        ParkingFloor floor = new ParkingFloor(floorNumber);
        floor.setId(nextId);
        parkingFloorMap.put(nextId, floor);
        nextId++;
        return floor;
    }
}

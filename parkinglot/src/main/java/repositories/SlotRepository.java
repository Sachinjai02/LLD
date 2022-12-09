package repositories;

import models.ParkingSlot;

import java.util.HashMap;
import java.util.Map;

public class SlotRepository {
    private Map<Long, ParkingSlot> slotMap = new HashMap<>();
    private Long nextId = 1l;

    public ParkingSlot getById(Long id) {
        return this.slotMap.get(id);
    }

    public ParkingSlot save(ParkingSlot parkingSlot) {
        parkingSlot.setId(nextId++);
        slotMap.put(parkingSlot.getId(), parkingSlot);
        return parkingSlot;
    }

    public void update(ParkingSlot parkingSlot) {
        slotMap.put(parkingSlot.getId(), parkingSlot);
    }
}

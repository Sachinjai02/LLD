package repositories;

import models.Gate;
import models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {

    private Map<Long, Gate> gateMap = new HashMap<>();
    private Long nextId = 1l;

    public Gate getById(Long id) {
        return this.gateMap.get(id);
    }

    public Gate save(Gate gate) {
        gate.setId(nextId++);
        gateMap.put(gate.getId(), gate);
        return gate;
    }

}

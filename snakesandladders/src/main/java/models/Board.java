package models;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int size;
    private Map<Integer, Entity> positionToEntityMap;

    public Board(int size) {
        this.size = size;
        this.positionToEntityMap = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Integer, Entity> getPositionToEntityMap() {
        return positionToEntityMap;
    }

    public void setPositionToEntityMap(Map<Integer, Entity> positionToEntityMap) {
        this.positionToEntityMap = positionToEntityMap;
    }
}

package models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {
    private int size;
    private Map<Integer, Entity> positionToEntityMap;



    private Map<Integer, Set<Drawable>> drawablesMap;
    public Board(int size) {
        this.size = size;
        this.positionToEntityMap = new HashMap<>();
        this.drawablesMap = new HashMap<>();
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

    public Map<Integer, Set<Drawable>> getDrawablesMap() {
        return drawablesMap;
    }

    public void setDrawablesMap(Map<Integer, Set<Drawable>> drawablesMap) {
        this.drawablesMap = drawablesMap;
    }
}

package models;

import exceptions.GameBuilderException;

public abstract class Entity implements Drawable {

    private int start;
    private int end;
    private EntityType entityType;
    protected Entity(EntityType entityType) {
        this.entityType = entityType;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public  void validate(int boardSize) {
        if(this.start == this.end) {
            throw new GameBuilderException("Entity "+ entityType.name() + " starts and end at same cell " + start);
        }
        if(this.start == 1 || this.end == 1) {
            throw new GameBuilderException("Entity should not begin/end at start position of the Board");
        }

        if(this.start == boardSize || this.end == boardSize) {
            throw new GameBuilderException("Entity should not begin/end at end position of the Board");
        }
    }

    @Override
    public String getColor() {
        return this.entityType.getColor();
    }

    @Override
    public String getId() {
        return this.entityType.name().charAt(0) + "[" + end + "]";
    }
}

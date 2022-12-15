package models;

import exceptions.GameBuilderException;

public class Ladders extends Entity{

    public Ladders() {
        super(EntityType.LADDER);
    }

    @Override
    public void validate(int boardSize) {
        super.validate(boardSize);
        if(this.getEnd() < this.getStart()) {
            throw new GameBuilderException("For a Ladder, end should be greater than start. It boosts!");
        }
    }
}

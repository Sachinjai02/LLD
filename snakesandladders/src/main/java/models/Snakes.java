package models;

import exceptions.GameBuilderException;

public class Snakes extends Entity{

    public Snakes() {
        super(EntityType.SNAKE);
    }

    @Override
    public void validate(int boardSize) {
        super.validate(boardSize);
        if(this.getEnd() > this.getStart()) {
            throw new GameBuilderException("For a Snake, start should be greater than end. It bites :P");
        }
    }
}

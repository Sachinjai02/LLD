package factories;

import models.Entity;
import models.EntityType;
import models.Ladders;
import models.Snakes;
import strategies.EntityValidationStrategy;
import strategies.LaddersValidationStrategy;
import strategies.SnakesValidationStrategy;

public class AbstractEntityFactory {

    public static Entity getEntityOfType(EntityType et) {
        return switch (et) {
            case SNAKE -> new Snakes();
            case LADDER -> new Ladders();
        };
    }

    public static EntityValidationStrategy getValidationStrategy(EntityType et) {
        return switch (et) {
            case SNAKE -> new SnakesValidationStrategy();
            case LADDER -> new LaddersValidationStrategy();
        };
    }
}

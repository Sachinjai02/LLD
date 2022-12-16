import controller.PlayGame;
import models.Entity;
import models.Game;
import models.Ladders;
import models.Snakes;
import strategies.DefaultHandleMoveStrategy;
import strategies.OneOrMaxDiceUnlockStrategy;

import java.util.*;

public class GameSimulator {


    public static void main(String[] args) {

        Game.GameBuilder gameBuilder = Game.getBuilder();

        List<Entity> entitiesList = new ArrayList<>();
        //5 snakes
        //4 ladders
        Set<Integer> placements = new HashSet<>();
        Random random = new Random();
        for(int i=0;i<5;++i) {
            Entity entity = new Snakes();
            int start = 0;
            int end = 0;
            while(! (end < start && ! placements.contains(start) && ! placements.contains(end))) {
                start = random.nextInt(3, 100);
                end = random.nextInt(3, 100);

            }
            placements.add(start);
            placements.add(end);
            entity.setStart(start);
            entity.setEnd(end);
            entitiesList.add(entity);
        }

        for(int i=0;i<4;++i) {
            Entity entity = new Ladders();
            int start = 0;
            int end = 0;
            while(! (end > start && ! placements.contains(start) && ! placements.contains(end))) {
                start = random.nextInt(3, 100);
                end = random.nextInt(3, 100);

            }
            placements.add(start);
            placements.add(end);
            entity.setStart(start);
            entity.setEnd(end);
            entitiesList.add(entity);
        }

        Game game =
        gameBuilder.setBoardSize(100)
        .setMaxDiceNumber(6)
        .setMoveStrategy(new DefaultHandleMoveStrategy())
                .setUnlockStrategy(new OneOrMaxDiceUnlockStrategy())
                .setPlayerNames(Arrays.asList(new String[]{"Sachin", "Nidhi"}))
                .setNumButtons(1)
                .setEntities(entitiesList)
                .build();

        new PlayGame().play(game);
    }

}

import controller.PlayGame;
import factories.AbstractEntityFactory;
import models.*;
import strategies.DefaultHandleMoveStrategy;
import strategies.EntityValidationStrategy;
import strategies.OneOrMaxDiceUnlockStrategy;

import java.util.*;

public class GameSimulator {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game.GameBuilder gameBuilder = Game.getBuilder();
        System.out.print("Enter the size of the board (>=100): ");
        int boardSize = scanner.nextInt();
        System.out.print("Enter the max Dice value to be assigned (>=6): ");
        int maxDiceValue = scanner.nextInt();


        System.out.print("Enter number of players : ");
        int numPlayers = scanner.nextInt();
        List<String> playerNames = new ArrayList<>();
        //This is added to skip the previous new line character inputted after number of players: nextInt() doesn't consume the new line char
        scanner.nextLine();

        for(int i=0;i<numPlayers;++i) {
            System.out.print("Enter name of player " + (i+1) + " : ");
            playerNames.add(scanner.nextLine());
        }
        System.out.print("Enter number of buttons per player (<=4): ");
        int numButtonsPerPlayer = scanner.nextInt();
        Game game =  gameBuilder
                    .setBoardSize(boardSize)
                    .setMaxDiceNumber(maxDiceValue)
                    .setMoveStrategy(new DefaultHandleMoveStrategy())
                            .setUnlockStrategy(new OneOrMaxDiceUnlockStrategy())
                            .setPlayerNames(playerNames)
                            .setNumberOfButtonsPerPlayer(numButtonsPerPlayer)
                            .setEntities(initializeSnakesAndLadders(scanner))
                            .build();

        new PlayGame().play(game);
    }

    private static List<Entity> initializeSnakesAndLadders(Scanner scanner) {
        Set<Integer> placements = new HashSet<>();
        Random random = new Random();
        List<Entity> entitiesList = new ArrayList<>();


        for(EntityType et : EntityType.values()) {
            System.out.print("Enter the total number of " + et.name() + " to be created inside the game ( < 10) : ");
            int numberEntity = scanner.nextInt();
            for(int i=0;i<numberEntity;++i) {
              //create entities which are empty for a start
              entitiesList.add(AbstractEntityFactory.getEntityOfType(et));
            }
        }
        //Randomizing each of entities on the board
        int totalEntitiesSize = entitiesList.size();
        while(totalEntitiesSize > 0) {
            int randomIdx = random.nextInt(totalEntitiesSize);
            Entity entity = entitiesList.get(randomIdx);
            EntityValidationStrategy validationStrategy = AbstractEntityFactory.getValidationStrategy(entity.getEntityType());
            int start = 0;
            int end = 0;
            while (!(validationStrategy.validate(start, end) && ! placements.contains(start) && ! placements.contains(end))) {
                start = random.nextInt(3, 100);
                end = random.nextInt(3, 100);
            }
            placements.add(start);
            placements.add(end);
            entity.setStart(start);
            entity.setEnd(end);
            //swap randomIdx and size-1 idx
            if(randomIdx != totalEntitiesSize-1) {
                Entity temp = entity;
                entitiesList.set(randomIdx, entitiesList.get(totalEntitiesSize - 1));
                entitiesList.set(totalEntitiesSize - 1, temp);
            }
            --totalEntitiesSize;
        }
        return entitiesList;
    }

}

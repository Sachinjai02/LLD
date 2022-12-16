package strategies;

import models.*;

import java.util.Map;

public class DefaultHandleMoveStrategy implements HandleMoveStrategy {
    @Override
    public void processMove(Game game, Player player, Button button, int diceValue) {
        Board board = game.getBoard();
        int maxNumberOnBoard = board.getSize();
        Map<Integer, Entity> positionToEntityMap = board.getPositionToEntityMap();
        int newValue = button.getCurrPosition() + diceValue;
        if(newValue == maxNumberOnBoard) {
            button.setStatus(ButtonStatus.COMPLETED);
            button.setCurrPosition(maxNumberOnBoard);
            System.out.println("Hurray! Button finished...");
            //check if player's all buttons finished
            if(player.getButtonList().stream().allMatch(b -> b.getStatus() == ButtonStatus.COMPLETED)) {
                player.setStatus(PlayerStatus.COMPLETED);
                System.out.println("Congrats! " + player.getName() + " You have won! You are at position: "
                        + game.getLeaderBoard().getPlayersFinishedInOrder().size() +1 );
                game.getLeaderBoard().getPlayersFinishedInOrder().add(player);
            }

        } else if(newValue < maxNumberOnBoard) {
            if(positionToEntityMap.containsKey(newValue)) {
                Entity entity = positionToEntityMap.get(newValue);
                newValue = entity.getEnd();
                if(newValue > entity.getStart()) {
                    System.out.println("Wow! You are advanced to cell " + newValue);
                } else {
                    System.out.println("Alas! You are stung back to cell " + newValue);
                }
            } else {
                System.out.println("You move to cell " + newValue);
            }
            button.setCurrPosition(newValue);
        } else {
            System.out.println("Can't cross the board!");
        }

    }
}

package strategies;

import constants.GameConsoleConstants;
import models.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultHandleMoveStrategy implements HandleMoveStrategy {
    @Override
    public void processMove(Game game, Player player, Button button, int diceValue) {
        Board board = game.getBoard();
        int maxNumberOnBoard = board.getSize();
        Map<Integer, Entity> positionToEntityMap = board.getPositionToEntityMap();
        Map<Integer, Set<Drawable>> drawablesMap = board.getDrawablesMap();
        int newValue = button.getCurrPosition() + diceValue;
        String color = button.getColor();
        if(newValue == maxNumberOnBoard) {
            button.setStatus(ButtonStatus.COMPLETED);

            System.out.println(color  + "Hurray! Button finished..." + GameConsoleConstants.RESET);
            //check if player's all buttons finished
            if(player.getButtonList().stream().allMatch(b -> b.getStatus() == ButtonStatus.COMPLETED)) {
                player.setStatus(PlayerStatus.COMPLETED);
                System.out.println(color + "Congrats! " + player.getName() + " You have won! On the leaderboard, you are at position: "
                        + (game.getLeaderBoard().getPlayersFinishedInOrder().size() +1) +  GameConsoleConstants.RESET);
                game.getLeaderBoard().getPlayersFinishedInOrder().add(player);
            }

            drawablesMap.get(button.getCurrPosition()).remove(button);
            button.setCurrPosition(newValue);
            Set<Drawable> drawablesOnNewMove = drawablesMap.getOrDefault(newValue, new HashSet<>());
            drawablesMap.putIfAbsent(newValue, drawablesOnNewMove);
            drawablesOnNewMove.add(button);

        } else if(newValue < maxNumberOnBoard) {
            if(positionToEntityMap.containsKey(newValue)) {
                Entity entity = positionToEntityMap.get(newValue);
                newValue = entity.getEnd();
                if(newValue > entity.getStart()) {
                    System.out.println(color + "Yay! You ("+ button.getId() +") are advanced to cell : " + newValue + " from : " + button.getCurrPosition());
                } else {
                    System.out.println(GameConsoleConstants.RED + "Alas! You ("+ button.getId() +") + are stung to cell :" + newValue + " from : " + button.getCurrPosition());
                }
            } else {
                System.out.println(color + "You ("+ button.getId() +") have moved to cell : " + newValue + " from : " + button.getCurrPosition());
            }

            drawablesMap.get(button.getCurrPosition()).remove(button);
            button.setCurrPosition(newValue);
            Set<Drawable> drawablesOnNewMove = drawablesMap.getOrDefault(newValue, new HashSet<>());
            drawablesMap.putIfAbsent(newValue, drawablesOnNewMove);
            drawablesOnNewMove.add(button);
        } else {
            System.out.println(GameConsoleConstants.RED  + "Can't cross the board!");
        }

    }
}

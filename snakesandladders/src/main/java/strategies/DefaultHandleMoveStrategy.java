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
            //check if player's all buttons finished
            if(player.getButtonList().stream().allMatch(b -> b.getStatus() == ButtonStatus.COMPLETED)) {
                player.setStatus(PlayerStatus.COMPLETED);
                game.getLeaderBoard().getPlayersFinishedInOrder().add(player);
            }
            button.setCurrPosition(maxNumberOnBoard);
        } else if(newValue < maxNumberOnBoard) {
            if(positionToEntityMap.containsKey(newValue)) {
                newValue = positionToEntityMap.get(newValue).getEnd();
            }
            button.setCurrPosition(newValue);
        }

    }
}

package strategies;

import constants.GameConsoleConstants;
import models.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OneOrMaxDiceUnlockStrategy implements ButtonUnlockStrategy{
    @Override
    public void unlock(Game game, Button button, int diceValue, int maxNumber) {
        Board board = game.getBoard();
        String color = button.getColor();
        Map<Integer, Set<Drawable>> drawablesMap = board.getDrawablesMap();
        if(diceValue == 1 || diceValue == maxNumber) {
            drawablesMap.get(0).remove(button);
            Set<Drawable> drawablesOnOne = drawablesMap.getOrDefault(1, new HashSet<>());
            drawablesOnOne.add(button);
            drawablesMap.putIfAbsent(1, drawablesOnOne);
            button.setCurrPosition(1);
            button.setStatus(ButtonStatus.IN_GAME);

            System.out.println(color + button.getId() + " Button opened!" + GameConsoleConstants.RESET);
        } else {
            System.out.println(GameConsoleConstants.RED + "Can't open yet! Please try in next chance" + GameConsoleConstants.RESET);
        }
    }
}

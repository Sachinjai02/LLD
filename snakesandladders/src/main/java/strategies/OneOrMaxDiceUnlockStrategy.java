package strategies;

import models.Button;
import models.ButtonStatus;
import models.Player;

public class OneOrMaxDiceUnlockStrategy implements ButtonUnlockStrategy{
    @Override
    public void unlock(Player player, Button button, int diceValue, int maxNumber) {
        if(diceValue == 1 || diceValue == maxNumber) {
            button.setCurrPosition(1);
            button.setStatus(ButtonStatus.IN_GAME);
            System.out.println(button.getId() + " Button opened!");
        } else {
            System.out.println("Can't open yet! Please try in next chance :P");
        }
    }
}

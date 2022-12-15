package strategies;

import models.Button;
import models.Player;

public class OneOrMaxDiceUnlockStrategy implements ButtonUnlockStrategy{
    @Override
    public void unlock(Player player, Button button, int diceValue, int maxNumber) {
        if(diceValue == 1 || diceValue == maxNumber) {
            button.setCurrPosition(1);
        }
    }
}

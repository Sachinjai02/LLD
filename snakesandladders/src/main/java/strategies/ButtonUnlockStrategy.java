package strategies;

import models.Button;
import models.Player;

public interface ButtonUnlockStrategy {
    void unlock(Player player, Button button, int diceValue, int maxNumber);
}

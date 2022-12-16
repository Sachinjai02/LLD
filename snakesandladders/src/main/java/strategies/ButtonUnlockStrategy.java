package strategies;

import models.Button;
import models.Game;
import models.Player;

public interface ButtonUnlockStrategy {
    void unlock(Game game, Button button, int diceValue, int maxNumber);
}

package strategies;

import models.Board;
import models.Button;
import models.Player;

public interface HandleMoveStrategy {
    void processMove(Board board, Player player, Button button, int diceValue);
}

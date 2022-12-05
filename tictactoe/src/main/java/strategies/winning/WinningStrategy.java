package strategies.winning;

import models.Board;
import models.Move;

public interface WinningStrategy {
    public boolean checkForVictory(Move move, Board board);
}

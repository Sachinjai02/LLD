package strategies.winning;

import models.Board;
import models.Move;

public interface WinningStrategy {
    public void checkForVictory(Move move, Board board);
}

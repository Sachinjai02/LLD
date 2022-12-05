package strategies.winning;

import models.Board;
import models.Move;

public class RowWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkForVictory(Move move, Board board) {
        return false;
    }
}

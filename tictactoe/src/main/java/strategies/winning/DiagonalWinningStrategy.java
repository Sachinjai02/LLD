package strategies.winning;

import models.Board;
import models.Move;

public class DiagonalWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkForVictory(Move move, Board board) {
        return false;
    }
}

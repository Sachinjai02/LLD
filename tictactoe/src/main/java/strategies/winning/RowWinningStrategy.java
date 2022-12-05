package strategies.winning;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;

import java.util.List;

public class RowWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkForVictory(Move move, Board board) {
        int rowNum = move.getRow();
        List<Cell> currrowCells = board.getCells().get(rowNum);
        Player player = move.getPlayer();
        for(int i=0;i< currrowCells.size();++i) {
            if(currrowCells.get(i).getPlayer() != player) {
                return false;
            }
        }
        return true;
    }
}

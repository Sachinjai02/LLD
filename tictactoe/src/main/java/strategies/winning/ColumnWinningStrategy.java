package strategies.winning;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;

import java.util.List;

public class ColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkForVictory(Move move, Board board) {
        int column = move.getCol();
        Player player = move.getPlayer();
        List<List<Cell>> cells = board.getCells();
        for(int i=0;i<cells.size();++i) {
            if(cells.get(i).get(column).getPlayer() != player) {
                return false;
            }
        }
        return true;
    }
}

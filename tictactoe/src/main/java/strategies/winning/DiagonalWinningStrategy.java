package strategies.winning;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;

import java.util.List;

public class DiagonalWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkForVictory(Move move, Board board) {
        int row = move.getRow();
        int col = move.getCol();
        List<List<Cell>> cells = board.getCells();
        Player player = move.getPlayer();
        int dim = cells.size();
        //we are on right diagonal
        if(row + col == dim - 1) {
            for(int i=0;i<dim;++i) {
                if(cells.get(i).get(dim-1-i).getPlayer() != player) {
                    return false;
                }
            }
            return true;
        } else if( row == col) {
            //we are on left diagonal
            for(int i=0;i<dim;++i) {
                if(cells.get(i).get(i).getPlayer() != player) {
                    return false;
                }
            }
            return true;
        }

        return false;

    }
}

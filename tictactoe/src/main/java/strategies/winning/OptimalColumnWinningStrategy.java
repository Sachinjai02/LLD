package strategies.winning;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.List;

public class OptimalColumnWinningStrategy implements WinningStrategy{
    //map of player and colwise count of its moves
    private HashMap<Player, HashMap<Integer, Integer>> playerColWiseCountMap;

    public OptimalColumnWinningStrategy(){
        this.playerColWiseCountMap = new HashMap();
    }
    @Override
    public boolean checkForVictory(Move move, Board board) {
        int col = move.getCol();
        int size = board.getCells().size();
        Player player = move.getPlayer();
        HashMap<Integer, Integer> colWiseCountMap = playerColWiseCountMap.get(player);
        if(colWiseCountMap == null) {
            colWiseCountMap = new HashMap<>();
            playerColWiseCountMap.put(player, colWiseCountMap);
        }
        int numPlayersMoveOnCol = colWiseCountMap.getOrDefault(col, 0);
        colWiseCountMap.put(col,  numPlayersMoveOnCol + 1);

        return numPlayersMoveOnCol == size-1;

    }
}

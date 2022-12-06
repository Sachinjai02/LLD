package strategies.winning;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.List;

public class OptimalRowWinningStrategy implements WinningStrategy{
    //map of player and rowwise count of its moves
    private HashMap<Player, HashMap<Integer, Integer>> playerRowWiseCountMap;

    public OptimalRowWinningStrategy() {
        this.playerRowWiseCountMap = new HashMap<>();
    }

    @Override
    public boolean checkForVictory(Move move, Board board) {
        int row = move.getRow();
        int size = board.getCells().size();
        Player player = move.getPlayer();
        HashMap<Integer, Integer> rowWiseCountMap = playerRowWiseCountMap.get(player);
        if(rowWiseCountMap == null) {
            rowWiseCountMap = new HashMap<>();
            playerRowWiseCountMap.put(player, rowWiseCountMap);
        }
        int numPlayersMoveOnRow = rowWiseCountMap.getOrDefault(row, 0);
        rowWiseCountMap.put(row,  numPlayersMoveOnRow + 1);

        return numPlayersMoveOnRow == size-1;
    }
}

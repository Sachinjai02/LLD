package strategies.winning;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.List;

public class OptimalDiagonalWinningStrategy implements WinningStrategy{
    private HashMap<Player, Integer> leftDiagonalPlayerCountMap;
    private HashMap<Player, Integer> rightDiagonalPlayerCountMap;

    public OptimalDiagonalWinningStrategy() {
        this.leftDiagonalPlayerCountMap = new HashMap<>();
        this.rightDiagonalPlayerCountMap = new HashMap<>();
    }

    @Override
    public boolean checkForVictory(Move move, Board board) {
        int row = move.getRow();
        int col = move.getCol();
        Player player = move.getPlayer();
        int size = board.getCells().size();
        if(row == col) {
            Integer playerLeftDiagonalCount = leftDiagonalPlayerCountMap.getOrDefault(player, 0);
            leftDiagonalPlayerCountMap.put(player, playerLeftDiagonalCount+1);
            return playerLeftDiagonalCount == size-1;
        } else if(row + col == size -1) {
            Integer playerRightDiagonalCount = rightDiagonalPlayerCountMap.getOrDefault(player, 0);
            rightDiagonalPlayerCountMap.put(player, playerRightDiagonalCount+1);
            return playerRightDiagonalCount == size-1;
        }
        return false;

    }
}

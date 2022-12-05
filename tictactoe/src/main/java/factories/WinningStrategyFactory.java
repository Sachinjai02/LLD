package factories;

import models.WinningStrategyName;
import strategies.winning.ColumnWinningStrategy;
import strategies.winning.DiagonalWinningStrategy;
import strategies.winning.RowWinningStrategy;
import strategies.winning.WinningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getGameWinningStrategyByName(String winningStrategyName) {
        WinningStrategyName winningStrategyEnum = WinningStrategyName.valueOf(winningStrategyName.toUpperCase());
        return switch (winningStrategyEnum) {
            case DIAGONAL -> new DiagonalWinningStrategy();
            case COLUMN -> new ColumnWinningStrategy();
            case ROW ->  new RowWinningStrategy();
            default -> new RowWinningStrategy();
        };
    }
}

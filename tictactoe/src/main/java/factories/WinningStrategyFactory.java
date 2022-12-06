package factories;

import models.WinningStrategyName;
import strategies.winning.*;

public class WinningStrategyFactory {
    public static WinningStrategy getGameWinningStrategyByName(String winningStrategyName) {
        WinningStrategyName winningStrategyEnum = WinningStrategyName.valueOf(winningStrategyName.toUpperCase());
        return switch (winningStrategyEnum) {
            case DIAGONAL -> new DiagonalWinningStrategy();
            case COLUMN -> new ColumnWinningStrategy();
            case ROW ->  new RowWinningStrategy();
            case OPTROW -> new OptimalRowWinningStrategy();
            case OPTCOLUMN -> new OptimalColumnWinningStrategy();
            case OPTDIAGONAL -> new OptimalDiagonalWinningStrategy();
        };
    }
}

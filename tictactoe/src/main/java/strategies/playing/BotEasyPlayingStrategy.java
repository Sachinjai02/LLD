package strategies.playing;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;

import java.util.List;

public class BotEasyPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
        List<List<Cell>> cells = board.getCells();
        for(int i=0;i< cells.size();++i) {
            for(int j=0;j<cells.size();++j) {
                if(cells.get(i).get(j).getPlayer() == null) {
                    return new Move(i,j);
                }
            }
        }
        return null;
    }
}

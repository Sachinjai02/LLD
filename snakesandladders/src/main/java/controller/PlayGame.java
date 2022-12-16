package controller;

import models.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayGame {

    private static final String BLACK_UNDERLINED  = "\033[4;30m";
    public static final String RESET = "\033[0m";
    public void play(Game game) {
        List<Player> playersFinishedInOrder = game.getLeaderBoard().getPlayersFinishedInOrder();
        List<Player> players = game.getPlayerList();
        int numPlayers = players.size();

        while(game.getStatus() != GameStatus.FINISHED) {

            drawBoard(game);

            //get current player who needs to make a move
            int playerIdx = getNextPlayerWhoIsToMove(game);
            //Player make move should set the Button status, player status to finished if all his buttons are completed
            //Also add it to leaderboard

            Move move = players.get(playerIdx).makeMove(game);
            move.setPlayerIdx(playerIdx);
            game.getMoves().add(move);

            if(playersFinishedInOrder.size() == numPlayers-1) {
                game.setStatus(GameStatus.FINISHED);
            }

        }
    }

    private void drawBoard(Game game) {
        int sizeOfBoard = game.getBoard().getSize();
        Map<Integer, Entity> snakesAndOthers = game.getBoard().getPositionToEntityMap();
        Map<Integer, Set<Drawable>> drawablesMap = game.getBoard().getDrawablesMap();
        int maxThingsOnSingleCell = game.getPlayerList().size() * game.getNumButtons() * 4;

        //say 10 things in one line
        int r = Math.ceilDiv(sizeOfBoard,10);
        char[] spaces = new char[5];
        Arrays.fill(spaces, ' ');
        String spacesStr = new String(spaces);
        for(int i=0;i<r;++i) {
            for(int j=i*10;j < Math.min(i*10+10, sizeOfBoard);++j) {
                System.out.print( BLACK_UNDERLINED + String.format("%" + maxThingsOnSingleCell + "s",  +  (j+1) ));
            }
            System.out.println(RESET);
            for(int j=i*10;j < Math.min(i*10+10, sizeOfBoard);++j) {
                Set<Drawable> drawables = drawablesMap.get(j + 1);
                if(snakesAndOthers.containsKey(j+1)) {
                    Entity entity = snakesAndOthers.get(j+1);
                    System.out.print(entity.getColor() + String.format("%" + maxThingsOnSingleCell + "s" , entity.getId()));
                } else if(drawables != null && drawables.size() > 0) {
                   for(Drawable drawable : drawables) {
                       System.out.print(drawable.getColor() + String.format("%-" + maxThingsOnSingleCell + "s",  drawable.getId()));
                   }

                } else {
                    System.out.print(String.format("%" + maxThingsOnSingleCell + "s", spacesStr));
                }

            }
            System.out.println(RESET);
        }

    }

    private int getNextPlayerWhoIsToMove(Game game) {
        List<Player> players = game.getPlayerList();
        int numMoves = game.getMoves().size();
        Move lastMove =  numMoves > 0 ? game.getMoves().get(numMoves-1) : null;
        int lastPlayerIdx = lastMove != null ? lastMove.getPlayerIdx() : -1;
        int nextPlayerToMakeMove = (lastPlayerIdx+1)%players.size();
        while(players.get(nextPlayerToMakeMove).getStatus() != PlayerStatus.IN_GAME) {
            nextPlayerToMakeMove = (lastPlayerIdx+1)%players.size();
        }
        return nextPlayerToMakeMove;
    }
}

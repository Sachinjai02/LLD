package controller;

import models.*;

import java.util.List;

public class PlayGame {

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

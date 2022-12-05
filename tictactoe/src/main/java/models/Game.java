package models;

import exceptions.GameBuilderException;
import factories.WinningStrategyFactory;
import strategies.winning.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    private List<Player> playerList;
    private List<Move> moves;
    private int lastPlayerIdx;
    private List<WinningStrategy> winningStrategies;
    private Board board;
    private GameStatus status;
    private Player winner;
    private Game() {

    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public void start() {
        int maxMoves = (playerList.size() + 1) * (playerList.size() + 1);
        while(this.status == GameStatus.INPROGRESS) {
            printGame();
            //determine the next player
            int nextPlayerIdx = (lastPlayerIdx+1)%playerList.size();
            Player nextPlayer = playerList.get(nextPlayerIdx);
            System.out.println("Turn for " + nextPlayer.getName());
            Move move = nextPlayer.makeMove(board);
            Cell cellMoved = board.getCells().get(move.getRow()).get(move.getCol());
            //validate if the move was valid
            if(! (nextPlayer instanceof Bot)) {
                //validate if the move was valid
                if(cellMoved.getPlayer() != null) {
                    System.out.println("Invalid move! This cell is already filled. Please try again!");
                    continue;
                }
            }
            cellMoved.setPlayer(nextPlayer);
            move.setPlayer(nextPlayer);
            moves.add(move);
            lastPlayerIdx = nextPlayerIdx;

            //check victory or draw
            for(WinningStrategy strategy: winningStrategies) {
                if(strategy.checkForVictory(move, board)) {
                    this.status = GameStatus.FINISHED;
                    this.winner = nextPlayer;
                    printGame();
                    System.out.println("Congratulations " + this.winner.getName() + " ! You have won. ");
                    break;
                }
            }

            //check for draw
            if(moves.size() == maxMoves) {
                this.status = GameStatus.DRAWN;
                printGame();
                System.out.println("Alas! The game has resulted in a draw.");
                break;
            }
        }

    }

    private void printGame() {
        List<List<Cell>> cells = board.getCells();
        for(int i=0;i<cells.size();++i) {
            List<Cell> cols = cells.get(i);
            for(int j=0;j<cells.size();++j) {
                System.out.print( (cols.get(j).getPlayer() != null ?  cols.get(j).getPlayer().getSymbol().getCharacter() : "-") + " ");
            }
            System.out.println();
        }
    }


    public static class GameBuilder {

        private List<Player> playerList;
        private List<String> winningStrategies;


        public GameBuilder() {
        }

        public GameBuilder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public GameBuilder setWinningStrategies(List<String> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build()  {

            //perform all validations;
            if(this.playerList.size() < 2) {
                throw new GameBuilderException("At least there should be 2 players for a game !");
            }
            //validate each player has a unique symbol
            Set<Character> playerSymbols = new HashSet<>();
            for(int i=0;i<playerList.size();++i) {
                char ch = playerList.get(i).getSymbol().getCharacter();
                if(playerSymbols.contains(ch)) {
                    throw new GameBuilderException("Each of the players should have unique symbol");
                }
                playerSymbols.add(ch);
            }

            Game game = new Game();
            game.board = new Board(this.playerList.size()+1);
            game.lastPlayerIdx = -1;
            game.playerList = this.playerList;
            game.status = GameStatus.INPROGRESS;
            game.moves = new ArrayList<>();
            game.winningStrategies = this.winningStrategies
                    .stream()
                    .map(winningStrategyName -> WinningStrategyFactory.getGameWinningStrategyByName(winningStrategyName))
                    .collect(Collectors.toList());
            return game;
        }
    }


}

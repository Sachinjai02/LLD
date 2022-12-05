package models;

import exception.GameBuilderException;
import strategies.winning.WinningStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public static class GameBuilder {

        private List<Player> playerList;
        private List<Move> moves;
        private int lastPlayerIdx;
        private List<WinningStrategy> winningStrategies;
        private Board board;
        private GameStatus status;
        private Player winner;

        public GameBuilder() {
            this.lastPlayerIdx = -1;
            this.status = GameStatus.RUNNING;
        }

        public List<Player> getPlayerList() {
            return playerList;
        }

        public GameBuilder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public List<Move> getMoves() {
            return moves;
        }

        public GameBuilder setMoves(List<Move> moves) {
            this.moves = moves; return this;
        }

        public int getLastPlayerIdx() {
            return lastPlayerIdx;
        }

        public GameBuilder setLastPlayerIdx(int lastPlayerIdx) {
            this.lastPlayerIdx = lastPlayerIdx;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Board getBoard() {
            return board;
        }

        public GameBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public GameStatus getStatus() {
            return status;
        }

        public GameBuilder setStatus(GameStatus status) {
            this.status = status;
            return this;
        }

        public Player getWinner() {
            return winner;
        }

        public GameBuilder setWinner(Player winner) {
            this.winner = winner;
            return this;
        }

        public Game build() throws GameBuilderException {
            Game game = new Game();
            //perform all validations;
            if(this.playerList.size() != this.board.getDimension() - 1) {
                throw new GameBuilderException("Invalid number of players for board of size " + this.board.getDimension());
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

            game.board = this.board;
            game.lastPlayerIdx = this.lastPlayerIdx;
            game.playerList = this.playerList;
            game.status = this.status;
            game.winningStrategies = this.winningStrategies;

            return game;
        }
    }


}

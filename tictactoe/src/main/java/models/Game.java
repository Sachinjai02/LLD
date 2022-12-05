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
            game.status = GameStatus.STARTED;
            game.moves = new ArrayList<>();
            game.winningStrategies = this.winningStrategies
                    .stream()
                    .map(winningStrategyName -> WinningStrategyFactory.getGameWinningStrategyByName(winningStrategyName))
                    .collect(Collectors.toList());
            return game;
        }
    }


}

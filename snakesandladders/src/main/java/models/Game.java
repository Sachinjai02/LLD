package models;

import exceptions.GameBuilderException;

import java.util.*;

public class Game {

    private Board board;
    private Dice dice;
    private List<Player> playerList;
    private LeaderBoard leaderBoard;
    private int lastPlayerIdx;
    private GameStatus status;
    private int numButtons;

    private Game() {

    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private int boardSize;
        private int maxDiceNumber;
        private int numButtons;


        private List<Entity> entities;

        private List<String> playerNames;

        public List<String> getPlayerNames() {
            return playerNames;
        }

        public GameBuilder setPlayerNames(List<String> playerNames) {
            this.playerNames = playerNames;
            return this;
        }

        public int getBoardSize() {
            return boardSize;
        }

        public GameBuilder setBoardSize(int boardSize) {
            this.boardSize = boardSize;
            return this;
        }

        public int getMaxDiceNumber() {
            return maxDiceNumber;
        }

        public GameBuilder setMaxDiceNumber(int maxDiceNumber) {
            this.maxDiceNumber = maxDiceNumber;
            return this;
        }

        public int getNumButtons() {
            return numButtons;
        }

        public GameBuilder setNumButtons(int numButtons) {
            this.numButtons = numButtons;
            return this;
        }

        public Game build() {
            //validations
            if(this.maxDiceNumber < 6) {
                throw new GameBuilderException("Dice should have minimum of 6 faces");
            }

            if(this.boardSize < 100) {
                throw new GameBuilderException("Board should have at least size of 100");
            }

            if(this.playerNames == null || this.playerNames.size() < 2) {
                throw new GameBuilderException("There should be at least two players to play the game");
            }

            if(this.numButtons < 1) {
                throw new GameBuilderException("There should be at least 1 button for each Player");
            }

            //validate if entities do not collide
            /*
                1. start != end for any of them
                2. start, end != 1, maxNumber
                3. No two entities collide
             */
            Set<Integer> unique = new HashSet<>();
            if(entities != null) {
                for (Entity entity : entities) {
                    entity.validate(this.boardSize);
                    if(unique.contains(entity.getStart()) || unique.contains(entity.getEnd())) {
                        throw new GameBuilderException("Two entities position should not conflict with each other");
                    }
                    unique.add(entity.getStart());
                    unique.add(entity.getEnd());
                }
            }

            Game game = new Game();
            //Board
            Board board = new Board(boardSize);
            Map<Integer, Entity> positionToEntityMap = board.getPositionToEntityMap();
            for(Entity entity : entities) {
                positionToEntityMap.put(entity.getStart(), entity);
            }

            List<Player> players = new ArrayList<>();

            for(String name: playerNames) {
                Player player = new Player(numButtons, name);
                players.add(player);
            }

            game.board = board;
            game.status = GameStatus.IN_PROGRESS;
            game.lastPlayerIdx = -1;
            game.dice = new Dice(maxDiceNumber);
            game.leaderBoard = new LeaderBoard();
            game.numButtons = numButtons;
            game.playerList = players;

            return game;
        }

        public List<Entity> getEntities() {
            return entities;
        }

        public GameBuilder setEntities(List<Entity> entities) {
            this.entities = entities;
            return this;
        }
    }
}

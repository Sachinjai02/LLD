package models;

import exceptions.GameBuilderException;
import strategies.ButtonUnlockStrategy;
import strategies.HandleMoveStrategy;

import java.util.*;

public class Game {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    private static final String[] colors = new String[] { BLACK,
            GREEN, RED,  BLUE, YELLOW, BLACK, YELLOW, PURPLE,
            CYAN, WHITE };
    private Board board;
    private Dice dice;
    private List<Player> playerList;
    private LeaderBoard leaderBoard;

    private Map<Integer, Set<Drawable>> drawablesMap;

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    private GameStatus status;
    private int numButtons;

    private ButtonUnlockStrategy unlockStrategy;

    private HandleMoveStrategy moveStrategy;

    private List<Move> moves;

    private Game() {

    }

    public Board getBoard() {
        return board;
    }

    public Dice getDice() {
        return dice;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }


    public GameStatus getStatus() {
        return status;
    }

    public int getNumButtons() {
        return numButtons;
    }

    public ButtonUnlockStrategy getUnlockStrategy() {
        return unlockStrategy;
    }

    public HandleMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public List<Move> getMoves() {
        return moves;
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

        private ButtonUnlockStrategy unlockStrategy;

        private HandleMoveStrategy moveStrategy;

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
                2. start, end != 1 && start, end != maxNumber
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
            Map<Integer, Set<Drawable>> map = new HashMap<>();
            for(Entity entity : entities) {
                positionToEntityMap.put(entity.getStart(), entity);
            }

            List<Player> players = new ArrayList<>();

            Set<Drawable> allButtons = new HashSet<>();
            for(String name: playerNames) {
                int idx = players.size();
                Player player = new Player(numButtons,
                        name, Character.valueOf( (char) ('A' + (idx+1)))
                , idx < colors.length ? colors[idx] : "");
                players.add(player);
                allButtons.addAll(player.getButtonList());
            }
            map.put(0, allButtons);

            game.board = board;
            game.status = GameStatus.IN_PROGRESS;
            game.dice = new Dice(maxDiceNumber);
            game.leaderBoard = new LeaderBoard();
            game.numButtons = numButtons;
            game.playerList = players;
            game.moveStrategy = moveStrategy;
            game.unlockStrategy = unlockStrategy;
            game.moves = new ArrayList<>();
            game.drawablesMap = map;

            return game;
        }

        public List<Entity> getEntities() {
            return entities;
        }

        public GameBuilder setEntities(List<Entity> entities) {
            this.entities = entities;
            return this;
        }

        public ButtonUnlockStrategy getUnlockStrategy() {
            return unlockStrategy;
        }

        public GameBuilder setUnlockStrategy(ButtonUnlockStrategy unlockStrategy) {
            this.unlockStrategy = unlockStrategy;
            return this;
        }

        public HandleMoveStrategy getMoveStrategy() {
            return moveStrategy;
        }

        public GameBuilder setMoveStrategy(HandleMoveStrategy moveStrategy) {
            this.moveStrategy = moveStrategy;
            return this;
        }
    }
}
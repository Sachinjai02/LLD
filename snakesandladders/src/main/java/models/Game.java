package models;

import exceptions.GameBuilderException;
import strategies.ButtonUnlockStrategy;
import strategies.HandleMoveStrategy;

import java.util.*;
import static constants.GameConsoleConstants.*;
public class Game {


    private static final String[] colors = new String[] {
            BLUE, YELLOW, GREEN, PURPLE,
            CYAN, WHITE, BLACK, RED};
    private Board board;
    private Dice dice;
    private List<Player> playerList;
    private LeaderBoard leaderBoard;



    public void setStatus(GameStatus status) {
        this.status = status;
    }

    private GameStatus status;
    private int numberOfButtonsPerPlayer;

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

    public int getNumberOfButtonsPerPlayer() {
        return numberOfButtonsPerPlayer;
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
        private int numberOfButtonsPerPlayer;


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

            if(this.numberOfButtonsPerPlayer < 1) {
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
            Map<Integer, Set<Drawable>> drawablesMap = board.getDrawablesMap();
            for(Entity entity : entities) {
                positionToEntityMap.put(entity.getStart(), entity);
            }

            List<Player> players = new ArrayList<>();

            Set<Drawable> allButtons = new HashSet<>();
            for(String name: playerNames) {
                int idx = players.size();
                Player player = new Player(numberOfButtonsPerPlayer,
                        name, Character.valueOf( (char) ('A' + (idx)))
                , idx < colors.length ? colors[idx] : "");
                players.add(player);
                allButtons.addAll(player.getButtonList());
            }
            drawablesMap.put(0, allButtons);

            game.board = board;
            game.status = GameStatus.IN_PROGRESS;
            game.dice = new Dice(maxDiceNumber);
            game.leaderBoard = new LeaderBoard();
            game.numberOfButtonsPerPlayer = numberOfButtonsPerPlayer;
            game.playerList = players;
            game.moveStrategy = moveStrategy;
            game.unlockStrategy = unlockStrategy;
            game.moves = new ArrayList<>();


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

        public int getNumberOfButtonsPerPlayer() {
            return numberOfButtonsPerPlayer;
        }

        public GameBuilder setNumberOfButtonsPerPlayer(int numberOfButtonsPerPlayer) {
            this.numberOfButtonsPerPlayer = numberOfButtonsPerPlayer;
            return this;
        }
    }
}

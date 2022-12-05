package client;

import exceptions.GameBuilderException;
import factories.PlayerFactory;
import models.*;

import java.util.*;


public class PlayGame {

    public static void main(String[] args) throws GameBuilderException {
        Game game = constructGame();
        game.start();
    }

    private static Game constructGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of players");
        int numHumans = Integer.parseInt(scanner.nextLine());

        List<Player> players = new ArrayList<>();

        for(int i=0;i<numHumans;++i) {
            System.out.println("Enter name of the player " + (i+1));
            String name = scanner.nextLine();
            System.out.println("Enter symbol (1 char) for the player" + (i+1));
            String symbol = scanner.nextLine();
            Player player = PlayerFactory.createHumanPlayer(name, symbol.charAt(0));
            players.add(player);
        }

        System.out.println("Enter the number of bots");
        int numBots = Integer.parseInt(scanner.nextLine());

        for(int i=0;i<numBots;++i) {
            System.out.println("Enter name of the Bot " + (i+1));
            String name = scanner.nextLine();
            System.out.println("Enter symbol (1 char) for the bot" + (i+1));
            String symbol = scanner.nextLine();
            System.out.println("Enter difficulty level for the bot" + (i+1));
            String difficultyLevel = scanner.nextLine();
            Player player = PlayerFactory.createBot(name, symbol.charAt(0), difficultyLevel);
            players.add(player);
        }

        List<String> winningStrategiesNames = new ArrayList<>();
        //For now hard coding winning strategies
        winningStrategiesNames.add("row");
        winningStrategiesNames.add("column");
        winningStrategiesNames.add("diagonal");

       return  Game.getBuilder()
                .setPlayerList(players)
                .setWinningStrategies(winningStrategiesNames)
                .build();

    }
}

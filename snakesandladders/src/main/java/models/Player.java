package models;

import constants.GameConsoleConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player  {


    private List<Button> buttonList;
    private String name;
    private Character charId;
    private Button lastButtonMoved;
    private PlayerStatus status;

    private String color;

    public Player(int numButtons, String name, Character id, String color) {
        this.buttonList = new ArrayList<>();
        this.charId = id;
        this.name = name;
        this.color = color;
        for(int i=0;i<numButtons;++i) {
            Button button = new Button(id.toString() + (i+1), this);
            this.buttonList.add(button);
        }
        this.status = PlayerStatus.IN_GAME;
    }

    public Move makeMove(Game game) {
        int numberOfButtons = game.getNumberOfButtonsPerPlayer();

        int buttonIdx = 1;
        Scanner scanner = new Scanner(System.in);

        System.out.print(this.color + "Hey " + this.name + "! Press enter to roll the dice: ");
        scanner.nextLine();
        System.out.println(this.color  + "Rolling the dice ...");
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int diceValue = game.getDice().roll();
        System.out.println(this.color + "Got " + diceValue + "! ");

        if(numberOfButtons > 1) {
            System.out.print(this.color + "Please enter which button to move [1-" + numberOfButtons + "]: ");
            buttonIdx = scanner.nextInt();
            while(buttonIdx < 1 || buttonIdx > numberOfButtons) {
                System.out.println(GameConsoleConstants.RED + "Invalid button chosen, please select correct button to move [1-" + game.getNumberOfButtonsPerPlayer() + "] : ");
                buttonIdx = scanner.nextInt();
            }
            while(this.buttonList.get(buttonIdx-1).getStatus() == ButtonStatus.COMPLETED) {
                System.out.println(GameConsoleConstants.RED + "This button is already finished, please select another button to move [1-" + game.getNumberOfButtonsPerPlayer() + "] : ");
                buttonIdx = scanner.nextInt();
            }
        }

        Button selectedButton = this.buttonList.get(buttonIdx-1);
        Move move = new Move(selectedButton, selectedButton.getCurrPosition());

        if(selectedButton.getStatus().equals(ButtonStatus.LOCKED)) {
            game.getUnlockStrategy().unlock(game, selectedButton, diceValue, game.getDice().getMaxNumber());
        } else {
            game.getMoveStrategy().processMove(game, this, selectedButton, diceValue);
        }
        return move;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}

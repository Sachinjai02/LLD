package models;

import java.util.ArrayList;
import java.util.List;
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
            Button button = new Button(id.toString() + i, this);
            this.buttonList.add(button);
        }
        this.status = PlayerStatus.IN_GAME;
    }

    public Move makeMove(Game game) {

        System.out.println("Hey " + this.name + "! Please enter which button to move");
        Scanner scanner = new Scanner(System.in);
        int buttonIdx = scanner.nextInt();
        while(this.buttonList.get(buttonIdx).getStatus() == ButtonStatus.COMPLETED) {
            System.out.println("This button is already finished, please try again");
            buttonIdx = scanner.nextInt();
        }
        Button selectedButton = this.buttonList.get(buttonIdx);
        Move move = new Move(selectedButton, selectedButton.getCurrPosition());
        int diceValue = game.getDice().roll();
        System.out.println("Rolling the dice ... Got " + diceValue);

        if(selectedButton.getStatus().equals(ButtonStatus.LOCKED)) {
            game.getUnlockStrategy().unlock(this, selectedButton, diceValue, game.getDice().getMaxNumber());
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

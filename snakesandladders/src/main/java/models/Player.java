package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

    private List<Button> buttonList;
    private String name;
    private Button lastButtonMoved;
    private PlayerStatus status;

    public Player(int numButtons, String name) {
        this.buttonList = new ArrayList<>();
        this.name = name;
        for(int i=0;i<numButtons;++i) {
            Button button = new Button();
            this.buttonList.add(button);
        }
        this.status = PlayerStatus.IN_GAME;
    }

    public Move makeMove(Game game) {

        System.out.println("Enter which button to move");
        Scanner scanner = new Scanner(System.in);
        int buttonIdx = scanner.nextInt();
        while(this.buttonList.get(buttonIdx).getStatus() != ButtonStatus.COMPLETED) {
            System.out.println("This button is already finished, please try again");
            buttonIdx = scanner.nextInt();
        }
        Button selectedButton = this.buttonList.get(buttonIdx);
        Move move = new Move(selectedButton, selectedButton.getCurrPosition());
        int diceValue = game.getDice().roll();
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

}

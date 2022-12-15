package models;

import java.util.ArrayList;
import java.util.List;

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
}

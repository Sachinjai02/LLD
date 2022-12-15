package models;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard {
    private List<Player> playersFinishedInOrder;
    public LeaderBoard() {
        this.playersFinishedInOrder = new ArrayList<>();
    }

    public List<Player> getPlayersFinishedInOrder() {
        return playersFinishedInOrder;
    }

    public void setPlayersFinishedInOrder(List<Player> playersFinishedInOrder) {
        this.playersFinishedInOrder = playersFinishedInOrder;
    }
}

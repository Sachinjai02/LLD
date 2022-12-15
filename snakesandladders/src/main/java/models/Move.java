package models;

public class Move {
    private Button button;

    //adding these to support undo operation (may get removed)
    private int prevButtonCellNumber;
    private int playerIdx;

    public Move(Button button, int prevButtonCellNumber) {
        this.button = button;
        this.prevButtonCellNumber = prevButtonCellNumber;

    }


    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getPrevButtonCellNumber() {
        return prevButtonCellNumber;
    }

    public void setPrevButtonCellNumber(int prevButtonCellNumber) {
        this.prevButtonCellNumber = prevButtonCellNumber;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }
}

package models;

public class Button {
    private int currPosition;
    private ButtonStatus status;

    public Button() {
        this.status = ButtonStatus.LOCKED;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }

    public ButtonStatus getStatus() {
        return status;
    }

    public void setStatus(ButtonStatus status) {
        this.status = status;
    }
}

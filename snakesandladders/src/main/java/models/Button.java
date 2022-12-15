package models;

public class Button {
    private int currPosition;
    private int lastPosition;
    private ButtonStatus status;

    public Button() {
        this.status = ButtonStatus.LOCKED;
    }
}

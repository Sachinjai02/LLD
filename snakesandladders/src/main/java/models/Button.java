package models;

public class Button implements Drawable {
    private int currPosition;
    private ButtonStatus status;
    private String id;

    private Player parent;

    public Button(String id, Player player) {
        this.status = ButtonStatus.LOCKED;
        this.id = id;
        this.parent = player;
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

    @Override
    public String getColor() {
        return this.parent.getColor();
    }

    public String getId() {
        return id;
    }

    public Player getParent() {
        return parent;
    }
}

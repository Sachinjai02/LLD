package models;

public class Refil {
    private Ink ink;
    private Nib nib;
    private RefilType type;

    public Ink getInk() {
        return ink;
    }

    public void setInk(Ink ink) {
        this.ink = ink;
    }

    public Nib getNib() {
        return nib;
    }

    public void setNib(Nib nib) {
        this.nib = nib;
    }

    public RefilType getType() {
        return type;
    }

    public void setType(RefilType type) {
        this.type = type;
    }
}

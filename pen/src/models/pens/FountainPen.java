package models.pens;

import models.Ink;
import models.Nib;
import models.Refillable;
import writestrategies.WriteStrategy;

public class FountainPen extends Pen implements Refillable {
    Ink ink;
    Nib nib;

    public FountainPen(WriteStrategy writeStrategy) {
        super(writeStrategy);
    }

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

    @Override
    public void refill() {

    }
}

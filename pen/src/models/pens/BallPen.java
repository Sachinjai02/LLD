package models.pens;

import models.Refil;
import writestrategies.WriteStrategy;

public class BallPen extends Pen {
    Refil refil;

    public BallPen(WriteStrategy writeStrategy) {
        super(writeStrategy);
    }

    public Refil getRefil() {
        return refil;
    }

    public void setRefil(Refil refil) {
        this.refil = refil;
    }
}

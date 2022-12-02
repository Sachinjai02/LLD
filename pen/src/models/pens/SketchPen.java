package models.pens;

import models.Refil;
import writestrategies.WriteStrategy;

public class SketchPen extends Pen {
    Refil refil;

    public SketchPen(WriteStrategy writeStrategy) {
        super(writeStrategy);
    }

    public Refil getRefil() {
        return refil;
    }

    public void setRefil(Refil refil) {
        this.refil = refil;
    }
}

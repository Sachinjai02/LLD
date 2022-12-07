package models;

public class ElectricSlot extends BaseModel {
    private Meter meter;

    private Slot baseSlot;

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public Slot getBaseSlot() {
        return baseSlot;
    }

    public void setBaseSlot(Slot baseSlot) {
        this.baseSlot = baseSlot;
    }
}

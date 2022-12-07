package models;

public class ElectricSlot extends Slot {
    private Meter meter;

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }
}

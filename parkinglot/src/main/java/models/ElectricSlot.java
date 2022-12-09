package models;

public class ElectricSlot extends BaseModel {
    private Meter meter;

    private ParkingSlot baseParkingSlot;

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public ParkingSlot getBaseSlot() {
        return baseParkingSlot;
    }

    public void setBaseSlot(ParkingSlot baseParkingSlot) {
        this.baseParkingSlot = baseParkingSlot;
    }
}

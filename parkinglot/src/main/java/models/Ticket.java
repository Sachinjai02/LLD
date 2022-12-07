package models;

public class Ticket extends BaseModel {
    private Operator operator;
    private Vehicle vehicle;
    private Gate entryGate;
    private Bill bill;
    private long entryTime;
    private Slot allotedSlot;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Gate getEntryGate() {
        return entryGate;
    }

    public void setEntryGate(Gate entryGate) {
        this.entryGate = entryGate;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public Slot getAllotedSlot() {
        return allotedSlot;
    }

    public void setAllotedSlot(Slot allotedSlot) {
        this.allotedSlot = allotedSlot;
    }
}

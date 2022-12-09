package models;

import java.util.Date;

public class Ticket extends BaseModel {
    private Operator operator;
    private Vehicle vehicle;
    private Gate entryGate;
    private Bill bill;
    private Date entryTime;
    private ParkingSlot allotedParkingSlot;

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

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public ParkingSlot getAllotedSlot() {
        return allotedParkingSlot;
    }

    public void setAllotedSlot(ParkingSlot allotedParkingSlot) {
        this.allotedParkingSlot = allotedParkingSlot;
    }
}

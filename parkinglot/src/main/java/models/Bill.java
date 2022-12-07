package models;

import java.util.List;

public class Bill extends BaseModel {
    private Ticket ticket;
    private BillStatus status;
    private double totalAmount;
    private List<Payment> paymentList;
    private Operator generatedBy;
    private Gate existGate;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Operator getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(Operator generatedBy) {
        this.generatedBy = generatedBy;
    }

    public Gate getExistGate() {
        return existGate;
    }

    public void setExistGate(Gate existGate) {
        this.existGate = existGate;
    }
}

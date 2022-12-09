package dtos;

import models.Ticket;

public class GenerateTicketResponseData {
    private Ticket ticket;
    public GenerateTicketResponseData(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

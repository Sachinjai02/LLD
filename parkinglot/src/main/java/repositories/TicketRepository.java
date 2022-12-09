package repositories;

import models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long, Ticket> ticketMap = new HashMap<>();
    private Long nextId = 1l;

    public Ticket getById(Long id) {
        return this.ticketMap.get(id);
    }

    public Ticket save(Ticket ticket) {
        ticket.setId(nextId++);
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }

}

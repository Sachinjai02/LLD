package com.study.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Ticket extends BaseModel {

    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    private Date bookingTime;
    private double amount;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
    @ManyToMany
    private List<ShowSeat> showSeatList;
    @OneToMany(mappedBy = "ticket")
    private List<Payment> paymentList;

}

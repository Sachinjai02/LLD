package com.study.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Payment extends BaseModel{
    private double amount;
    @ManyToOne()
    private Ticket ticket;
    private String refNum; //razorPayId, payTm transaction id etc
    private Date paymentTime;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;

}

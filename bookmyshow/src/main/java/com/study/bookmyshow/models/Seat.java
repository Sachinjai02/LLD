package com.study.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.Order;
import lombok.Data;

@Entity
@Data
public class Seat extends BaseModel {
    private int row;
    private int col;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    @Enumerated(EnumType.ORDINAL)
    private SeatStatus status;
    private String name;

}

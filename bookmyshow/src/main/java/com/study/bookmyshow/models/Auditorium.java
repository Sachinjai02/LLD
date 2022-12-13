package com.study.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Auditorium extends BaseModel {
    private String name;
    @OneToMany
    private List<Seat> seatList;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> featureList;
}

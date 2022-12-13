package com.study.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Theatre extends BaseModel {
    private String name;
    private String address;
    @OneToMany( fetch = FetchType.EAGER)
    private List<Auditorium> auditoriumList;
    @ManyToOne
    private City city;
}

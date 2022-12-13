package com.study.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class City extends BaseModel {
    private String name;
}

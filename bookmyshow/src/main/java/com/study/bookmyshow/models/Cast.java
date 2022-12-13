package com.study.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Cast extends BaseModel{
    private String name;
}

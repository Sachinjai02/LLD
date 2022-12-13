package com.study.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="casts")
public class Cast extends BaseModel{
    private String name;
}

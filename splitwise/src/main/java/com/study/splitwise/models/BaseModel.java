package com.study.splitwise.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

}

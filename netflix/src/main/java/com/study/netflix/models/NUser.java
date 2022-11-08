package com.study.netflix.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class NUser extends BaseModel {
    private String name;
    private String email;
}

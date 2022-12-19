package com.study.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User extends BaseModel{
    private String name;
    private String phoneNumber;
    private String pwd;

}

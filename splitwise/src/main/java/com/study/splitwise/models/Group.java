package com.study.splitwise.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="groups")
public class Group extends BaseModel {
    private String name;
    private String description;
    @OneToMany
    private List<User> users;
    @OneToOne
    private User createdBy;
    private Date createdAt;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Expense> expenses;
}

package com.study.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Expense extends BaseModel {
    private double amount;
    @OneToOne
    private User createdBy;
    private String description;
    private Date createdAt;
    @OneToMany
    private List<UserExpense> paidBy;
    @OneToMany
    private List<UserExpense> owedBy;
}

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
    private String title;
    private double amount;
    @OneToOne
    private User createdBy;
    private String description;
    private Date createdAt;
    @OneToMany(mappedBy = "expense")
    private List<UserExpense> userExpenses;

}

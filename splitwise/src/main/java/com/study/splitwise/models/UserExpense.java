package com.study.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UserExpense extends BaseModel {
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    private double amount;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
}

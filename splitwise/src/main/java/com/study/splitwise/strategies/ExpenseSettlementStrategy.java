package com.study.splitwise.strategies;

import com.study.splitwise.controllers.pojos.Transaction;
import com.study.splitwise.models.UserExpense;

import java.util.List;

public interface ExpenseSettlementStrategy {
    public List<Transaction> createTransactionsForSettlement(List<UserExpense> expenses);
}

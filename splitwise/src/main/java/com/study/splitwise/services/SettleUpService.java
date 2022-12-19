package com.study.splitwise.services;

import com.study.splitwise.controllers.pojos.Transaction;
import com.study.splitwise.models.Expense;
import com.study.splitwise.models.UserExpense;
import com.study.splitwise.repositories.GroupRepository;
import com.study.splitwise.repositories.UserExpenseRepository;
import com.study.splitwise.strategies.ExpenseSettlementStrategy;
import com.study.splitwise.strategies.MinMaxExpenseSettlementStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettleUpService {
    private UserExpenseRepository userExpenseRepository;
    private GroupRepository groupRepository;
    private ExpenseSettlementStrategy expenseSettlementStrategy;


    @Autowired
    public SettleUpService(UserExpenseRepository userExpenseRepository, GroupRepository groupRepository, ExpenseSettlementStrategy expenseSettlementStrategy) {
        this.userExpenseRepository = userExpenseRepository;
        this.groupRepository = groupRepository;
        this.expenseSettlementStrategy = expenseSettlementStrategy;
    }

    public List<Transaction> settleUser(Long userId) {
        //Find all userExpenses for a user from userexpense table;
        List<UserExpense> userExpenses = userExpenseRepository.findAllByUser(userId);
        //find all expenses for each of these userexpenses
        List<Expense> expenses = userExpenses.stream().map(userex -> userex.getExpense()).collect(Collectors.toList());
        return expenseSettlementStrategy.createTransactionsForSettlement(userExpenseRepository.findAllByExpenseIn(expenses));
    }

    public List<Transaction> settleGroup(Long groupId) {
        //Find all expenses corresponding to a group;
        List<Expense> expenses = groupRepository.findById(groupId).get().getExpenses();
        return expenseSettlementStrategy.createTransactionsForSettlement(userExpenseRepository.findAllByExpenseIn(expenses));
    }

}

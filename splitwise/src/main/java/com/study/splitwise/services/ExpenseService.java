package com.study.splitwise.services;

import com.study.splitwise.models.Expense;
import com.study.splitwise.models.Group;
import com.study.splitwise.models.User;
import com.study.splitwise.models.UserExpense;
import com.study.splitwise.repositories.ExpenseRepository;
import com.study.splitwise.repositories.GroupRepository;
import com.study.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public Expense createExpense(Double amount, String name, String desc, Map<Long, Double> paidBy, Map<Long, Double> owedBy, Long createdById, Long groupId) {
        Expense expense = new Expense();
        expense.setCreatedAt(new Date());
        expense.setCreatedBy(userRepository.findById(createdById).get());
        expense.setDescription(desc);
        expense.setTitle(name);
        expense.setAmount(amount);
        List<UserExpense> paidByList = new ArrayList<>();
        List<UserExpense> owedByList = new ArrayList<>();

        Expense finalExp = expense;

        //Add paidBy entries
        userRepository.findAllById(paidBy.entrySet().stream()
                .map(entry -> entry.getKey()).collect(Collectors.toList()))
                .forEach(user -> {
                    UserExpense userExpense = new UserExpense();
                    userExpense.setUser(user);
                    userExpense.setExpense(finalExp);
                    userExpense.setAmount(paidBy.get(user.getId()));
                    paidByList.add(userExpense);
                });


        //Add owedBy entries
        userRepository.findAllById(owedBy.entrySet().stream()
                        .map(entry -> entry.getKey()).collect(Collectors.toList()))
                .forEach(user -> {
                    UserExpense userExpense = new UserExpense();
                    userExpense.setUser(user);
                    userExpense.setExpense(finalExp);
                    userExpense.setAmount(paidBy.get(user.getId()));
                    owedByList.add(userExpense);
                });
        expense.setPaidBy(paidByList);
        expense.setOwedBy(owedByList);

        //save expense
        expense = expenseRepository.save(expense);

        //save group
        if(groupId != null) {
            Group group = groupRepository.findById(groupId).get();
            group.getExpenses().size();
            group.getExpenses().add(expense);
            groupRepository.save(group);
        }

        return expense;
    }
}

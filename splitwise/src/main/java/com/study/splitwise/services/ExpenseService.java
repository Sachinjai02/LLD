package com.study.splitwise.services;

import com.study.splitwise.models.Expense;
import com.study.splitwise.models.ExpenseType;
import com.study.splitwise.models.Group;
import com.study.splitwise.models.UserExpense;
import com.study.splitwise.repositories.ExpenseRepository;
import com.study.splitwise.repositories.GroupRepository;
import com.study.splitwise.repositories.UserExpenseRepository;
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

    private UserExpenseRepository userExpenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, GroupRepository groupRepository, UserExpenseRepository userExpenseRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.userExpenseRepository = userExpenseRepository;
    }

    public Expense createExpense(Double amount, String name, String desc, Map<Long, Double> paidBy, Map<Long, Double> owedBy, Long createdById, Long groupId) {
        Expense expense = new Expense();
        expense.setCreatedAt(new Date());
        expense.setCreatedBy(userRepository.findById(createdById).get());
        expense.setDescription(desc);
        expense.setTitle(name);
        expense.setAmount(amount);

        //save expense
        Expense expenseDb = expenseRepository.save(expense);

        List<UserExpense> userExpenseList = new ArrayList<>();


        //Add paidBy entries
        userRepository.findAllById(paidBy.entrySet().stream()
                        .map(entry -> entry.getKey()).collect(Collectors.toList()))
                .forEach(user -> {
                    UserExpense userExpense = new UserExpense();
                    userExpense.setUser(user);
                    userExpense.setExpense(expenseDb);
                    userExpense.setExpenseType(ExpenseType.PAID);
                    userExpense.setAmount(paidBy.get(user.getId()));
                    userExpenseList.add(userExpense);
                });

        userRepository.findAllById(owedBy.entrySet().stream()
                        .map(entry -> entry.getKey()).collect(Collectors.toList()))
                .forEach(user -> {
                    UserExpense userExpense = new UserExpense();
                    userExpense.setUser(user);
                    userExpense.setExpense(expenseDb);
                    userExpense.setExpenseType(ExpenseType.OWES);
                    userExpense.setAmount(owedBy.get(user.getId()));
                    userExpenseList.add(userExpense);
                });


        expenseDb.setUserExpenses( userExpenseRepository.saveAll(userExpenseList));
        //save group
        if(groupId != null) {
            Group group = groupRepository.findById(groupId).get();
            group.getExpenses().add(expenseDb);
            groupRepository.save(group);
        }

        return expense;
    }
}

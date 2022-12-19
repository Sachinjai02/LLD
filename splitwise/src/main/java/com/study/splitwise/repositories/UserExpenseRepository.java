package com.study.splitwise.repositories;

import com.study.splitwise.models.Expense;
import com.study.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    public List<UserExpense> findAllByUser(Long id);
    public List<UserExpense> findAllByExpenseIn(Iterable<Expense> expenses);
}

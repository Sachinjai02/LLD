package com.study.splitwise.controllers;

import com.study.splitwise.dtos.BaseResponseDto;
import com.study.splitwise.dtos.CreateExpenseDto;
import com.study.splitwise.models.Expense;
import com.study.splitwise.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public BaseResponseDto<Expense> createExpense(CreateExpenseDto expenseDto) {
        BaseResponseDto<Expense> responseDto = new BaseResponseDto<>();
        try {
            responseDto.setData(expenseService.createExpense(expenseDto.getAmount(), expenseDto.getName(), expenseDto.getDesc(), expenseDto.getPaidBy()
            , expenseDto.getOwedBy(), expenseDto.getCreatedByUserId(), expenseDto.getGroupId()));
            responseDto.setStatus("Success");
        }catch(Exception e) {
            e.printStackTrace();
            responseDto.setStatus("Failed");
            responseDto.setMessage("Failure in expense creation : " + e.getMessage());
        }
        return responseDto;
    }
}

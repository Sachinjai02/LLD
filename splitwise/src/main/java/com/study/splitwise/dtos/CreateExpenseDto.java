package com.study.splitwise.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class CreateExpenseDto {
    private Double amount;
    private String name;
    private String desc;
    private Map<Long, Double> paidBy;
    private Map<Long, Double> owedBy;
    private Long createdByUserId;
    private Long groupId;


}

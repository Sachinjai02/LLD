package com.study.splitwise.dtos;

import java.util.Map;

public class CreateExpenseDto {
    private Double amount;
    private String name;
    private String desc;
    private Map<Long, Double> paidBy;
    private Map<Long, Double> owedBy;
    private Long createdByUserId;
    private Long groupId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<Long, Double> getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Map<Long, Double> paidBy) {
        this.paidBy = paidBy;
    }

    public Map<Long, Double> getOwedBy() {
        return owedBy;
    }

    public void setOwedBy(Map<Long, Double> owedBy) {
        this.owedBy = owedBy;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

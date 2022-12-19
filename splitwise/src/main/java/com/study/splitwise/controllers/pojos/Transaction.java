package com.study.splitwise.controllers.pojos;

import com.study.splitwise.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor

public class Transaction {
    private User payBy;
    private User payTo;
    private Double amount;

    public String toString() {
        return payBy.getName() + " -> " + payTo.getName() + " :[" + amount + "]";
    }
}

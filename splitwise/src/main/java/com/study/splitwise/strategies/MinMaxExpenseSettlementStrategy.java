package com.study.splitwise.strategies;

import com.study.splitwise.pojos.Transaction;
import com.study.splitwise.models.ExpenseType;
import com.study.splitwise.models.User;
import com.study.splitwise.models.UserExpense;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MinMaxExpenseSettlementStrategy implements ExpenseSettlementStrategy{

    public class Pair {
        private Double amount;
        private User user;
        public Pair(Double amount, User user) {
            this.user = user;
            this.amount = amount;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
    private final Double ZERO = 0.0D;
    @Override
    public List<Transaction> createTransactionsForSettlement(List<UserExpense> expenses) {
        Map<User, Double> userToAmount = new HashMap<>();
        expenses.forEach( userExpense -> {
            User user = userExpense.getUser();
            Double amount = userExpense.getExpenseType().equals(ExpenseType.PAID) ? userExpense.getAmount() : -userExpense.getAmount();
            amount += userToAmount.getOrDefault(user, ZERO);
            if(! ZERO.equals(amount)) {
                userToAmount.put(user, amount);
            } else {
                userToAmount.remove(user);
            }
        });

        //min heap of users which owe (-ve balance)
        PriorityQueue<Pair> usersWhichOwes = new PriorityQueue<Pair>((p1,p2) -> p1.getAmount() > p2.getAmount() ? 1 : -1);
        //max heap
        PriorityQueue<Pair> usersWhichPaid = new PriorityQueue<Pair>((p1,p2) -> p2.getAmount() > p1.getAmount() ? 1 : -1);

        userToAmount.entrySet().forEach(entry  -> {
            if(entry.getValue() > 0) {
                usersWhichPaid.offer(new Pair(entry.getValue(), entry.getKey()));
            } else {
                usersWhichOwes.offer(new Pair(entry.getValue(), entry.getKey()));
            }
        });

        List<Transaction> transactions = new ArrayList<>();
        while(usersWhichPaid.size() > 0 && usersWhichOwes.size() > 0) {
            Pair owe = usersWhichOwes.poll();
            Pair paid = usersWhichPaid.poll();
            if(-owe.getAmount() > paid.getAmount()) {
                Double additional = -owe.getAmount() - paid.getAmount();
                transactions.add(new Transaction(owe.getUser(), paid.getUser(), paid.getAmount()));
                owe.setAmount(-additional);
                usersWhichOwes.offer(owe);
            }else {
                Double additional = paid.getAmount() + owe.getAmount();
                transactions.add(new Transaction(owe.getUser(), paid.getUser(), -owe.getAmount()));
                paid.setAmount(additional);
                usersWhichPaid.offer(paid);
            }
        }

        return transactions;
    }
}

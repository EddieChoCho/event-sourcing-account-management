package com.eddie.eventsourcingaccountmanagement.dom;

import com.eddie.eventsourcingaccountmanagement.exception.BalanceException;
import com.eddie.eventsourcingaccountmanagement.model.AbstractEntity;
import lombok.Data;

@Data
public class BankAccount extends AbstractEntity {
    private String owner;
    private long balance;

    public BankAccount(long id){
        setId(id);
    }

    public void deposit(long deposit) {
        this.balance = this.balance + deposit;
    }

    public long withdrawal(long withdrawal) throws BalanceException {
        if (this.balance < withdrawal) {
            throw new BalanceException("There is not enough amount to withdrawal");
        }
        this.balance = this.balance - withdrawal;
        return withdrawal;
    }
}

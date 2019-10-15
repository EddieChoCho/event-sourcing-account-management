package com.eddie.eventsourcingaccountmanagement.dom;

import com.eddie.eventsourcingaccountmanagement.exception.BalanceException;
import lombok.Data;

@Data
public class BankAccount {
    private Long id;
    private String owner;
    private long balance;

    public BankAccount(long id){
        this.id = id;
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

    public void restoreFromSnapshot(BankAccountSnapshot snapshot){
        this.owner = snapshot.getOwner();
        this.balance = snapshot.getBalance();
    }
}

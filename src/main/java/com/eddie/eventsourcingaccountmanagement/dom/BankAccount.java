package com.eddie.eventsourcingaccountmanagement.dom;

import com.eddie.eventsourcingaccountmanagement.event.BankAccountCreated;
import com.eddie.eventsourcingaccountmanagement.event.DepositPerformed;
import com.eddie.eventsourcingaccountmanagement.event.WithdrawalPerformed;
import com.eddie.eventsourcingaccountmanagement.exception.BalanceException;
import lombok.Data;

@Data
public class BankAccount extends AbstractDomainObject {
    private Long id;
    private String owner;
    private long balance;

    public BankAccount(long id){
        this.id = id;
    }

    public void restoreFromSnapshot(BankAccountSnapshot snapshot){
        this.owner = snapshot.getOwner();
        this.balance = snapshot.getBalance();
    }

    @Override
    public void apply(BankAccountCreated event) {
        this.owner = event.getOwner();
    }

    @Override
    public void apply(DepositPerformed event) {
        this.balance += event.getAmount();
    }

    @Override
    public void apply(WithdrawalPerformed event) {
        if (this.balance < event.getAmount()) {
            throw new BalanceException("There is not enough amount to withdrawal");
        }
        this.balance -= event.getAmount();
    }
}

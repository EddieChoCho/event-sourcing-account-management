package com.eddie.eventsourcingaccountmanagement.event;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import lombok.Data;
import lombok.NonNull;

@Data
public class DepositPerformed implements AccountEvent {

    @NonNull
    private long amount;

    @Override
    public BankAccount apply(BankAccount account) {
        long amount = this.getAmount();
        account.deposit(amount);
        return account;
    }
}

package com.eddie.eventsourcingaccountmanagement.event;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import lombok.Data;
import lombok.NonNull;

@Data
public class BankAccountCreated implements AccountEvent {

    @NonNull
    private String owner;
    @NonNull
    private long amount;

    @Override
    public BankAccount apply(BankAccount account) {
        long amount = this.getAmount();
        String owner = this.getOwner();
        account.setBalance(amount);
        account.setOwner(owner);
        return account;
    }
}

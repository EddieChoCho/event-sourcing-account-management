package com.eddie.eventsourcingaccountmanagement.event;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.dom.BankAccountSnapshot;
import lombok.Data;
import lombok.NonNull;

@Data
public class BankAccountCreated extends AccountEvent {

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

    @Override
    public BankAccountSnapshot apply(BankAccountSnapshot snapshot) {
        long amount = this.getAmount();
        String owner = this.getOwner();
        long version = this.getVersion();
        snapshot.setOwner(owner);
        snapshot.deposit(amount);
        snapshot.setVersion(version);
        return snapshot;
    }
}

package com.eddie.eventsourcingaccountmanagement.event;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.dom.BankAccountSnapshot;
import lombok.Data;
import lombok.NonNull;

@Data
public class DepositPerformed extends AccountEvent {

    @NonNull
    private long amount;

    @Override
    public BankAccount apply(BankAccount account) {
        long amount = this.getAmount();
        account.deposit(amount);
        return account;
    }

    @Override
    public BankAccountSnapshot apply(BankAccountSnapshot snapshot) {
        long amount = this.getAmount();
        long version = this.getVersion();
        snapshot.deposit(amount);
        snapshot.setVersion(version);
        return snapshot;
    }
}

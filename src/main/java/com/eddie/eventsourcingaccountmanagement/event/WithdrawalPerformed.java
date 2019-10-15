package com.eddie.eventsourcingaccountmanagement.event;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.dom.BankAccountSnapshot;
import com.eddie.eventsourcingaccountmanagement.exception.BalanceException;
import lombok.Data;
import lombok.NonNull;

@Data
public class WithdrawalPerformed extends AccountEvent {

    @NonNull
    private long amount;

    @Override
    public BankAccount apply(BankAccount account) throws BalanceException {
        long amount = this.getAmount();
        account.withdrawal(amount);
        return account;
    }

    @Override
    public BankAccountSnapshot apply(BankAccountSnapshot snapshot) {
        long amount = this.getAmount();
        long version = this.getVersion();
        snapshot.withdrawal(amount);
        snapshot.setVersion(version);
        return snapshot;
    }
}

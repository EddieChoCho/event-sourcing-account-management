package com.eddie.eventsourcingaccountmanagement.command;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.event.WithdrawalPerformed;
import lombok.Data;
import lombok.NonNull;

@Data
public class Withdrawal implements Command {

    @NonNull
    private BankAccount account;
    @NonNull
    private long amount;

    @Override
    public AccountEvent apply() {
        WithdrawalPerformed event = new WithdrawalPerformed(amount);
        event.apply(account);
        return event;
    }
}

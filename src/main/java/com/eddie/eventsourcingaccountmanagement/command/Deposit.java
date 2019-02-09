package com.eddie.eventsourcingaccountmanagement.command;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.event.DepositPerformed;
import lombok.Data;
import lombok.NonNull;

@Data
public class Deposit implements Command {

    @NonNull
    private long amount;

    @Override
    public AccountEvent apply() {
        return new DepositPerformed(amount);
    }
}

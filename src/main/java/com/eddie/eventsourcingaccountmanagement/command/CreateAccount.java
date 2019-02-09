package com.eddie.eventsourcingaccountmanagement.command;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.event.BankAccountCreated;
import lombok.Data;
import lombok.NonNull;
@Data
public class CreateAccount implements Command {
    @NonNull
    private String owner;
    @NonNull
    private long amount;

    @Override
    public AccountEvent apply() {
        return new BankAccountCreated(owner, amount);
    }
}

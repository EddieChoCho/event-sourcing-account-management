package com.eddie.eventsourcingaccountmanagement.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class BankAccountCreated implements AccountEvent {

    @NonNull
    private String owner;

}

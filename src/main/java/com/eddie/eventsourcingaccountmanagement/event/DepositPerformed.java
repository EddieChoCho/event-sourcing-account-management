package com.eddie.eventsourcingaccountmanagement.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class DepositPerformed implements AccountEvent {

    @NonNull
    private long amount;

}

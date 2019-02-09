package com.eddie.eventsourcingaccountmanagement.model.enums;

import com.eddie.eventsourcingaccountmanagement.event.BankAccountCreated;
import com.eddie.eventsourcingaccountmanagement.event.DepositPerformed;
import com.eddie.eventsourcingaccountmanagement.event.WithdrawalPerformed;

public enum EventType {

    BankAccountCreated(BankAccountCreated.class),
    DepositPerformed(DepositPerformed.class),
    WithdrawalPerformed(WithdrawalPerformed.class);

    private Class eventClass;

    EventType(Class eventClass) {
        this.eventClass = eventClass;
    }

    public Class getEventClass() {
        return eventClass;
    }
}

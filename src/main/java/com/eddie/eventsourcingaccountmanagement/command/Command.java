package com.eddie.eventsourcingaccountmanagement.command;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;

public interface Command {

    AccountEvent apply();
}

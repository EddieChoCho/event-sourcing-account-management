package com.eddie.eventsourcingaccountmanagement.command;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.service.EventService;

public interface Command {

    AccountEvent apply();
}

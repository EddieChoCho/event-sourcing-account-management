package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.event.BankAccountCreated;
import com.eddie.eventsourcingaccountmanagement.event.DepositPerformed;
import com.eddie.eventsourcingaccountmanagement.event.WithdrawalPerformed;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {
    private EventService eventService;

    @Autowired
    public CommandService(EventService eventService){
        this.eventService = eventService;
    }

    public Event createAccount(Aggregate aggregate, String owner){
        AccountEvent event = new BankAccountCreated(owner);
        return eventService.saveEvent(event, aggregate);
    }

    public Event deposit(Aggregate aggregate, long amount){
        AccountEvent event = new DepositPerformed(amount);
        return eventService.saveEvent(event, aggregate);
    }

    public Event withdrawal(Aggregate aggregate, BankAccount account, long amount){
        AccountEvent event = new WithdrawalPerformed(amount);
        account.apply(event);
        return eventService.saveEvent(event, aggregate);
    }
}

package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    private EventService eventService;

    @Autowired
    public QueryService(EventService eventService){
        this.eventService = eventService;
    }

    public BankAccount getBankAccount(long aggregateId){
        List<AccountEvent> accountEvents = eventService.getAccountEvents(aggregateId);
        BankAccount account = new BankAccount(aggregateId);
        accountEvents.stream().forEach(event -> event.apply(account));
        return account;
    }
}

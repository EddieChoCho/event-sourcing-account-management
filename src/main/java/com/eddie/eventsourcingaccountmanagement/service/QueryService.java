package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccountSnapshot;
import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    private EventService eventService;
    private SnapShotService snapShotService;

    @Autowired
    public QueryService(EventService eventService, SnapShotService snapShotService){
        this.eventService = eventService;
        this.snapShotService = snapShotService;
    }

    public BankAccount getBankAccount(long aggregateId){
        List<AccountEvent> accountEvents = eventService.getAccountEvents(aggregateId);
        BankAccount account = new BankAccount(aggregateId);
        accountEvents.forEach(account::apply);
        return account;
    }

    public BankAccount getBankAccountWithSnapshot(long aggregateId){
        BankAccountSnapshot snapshot = snapShotService.getSnapshot(aggregateId);
        BankAccount account = new BankAccount(aggregateId);
        account.restoreFromSnapshot(snapshot);
        return account;
    }

}

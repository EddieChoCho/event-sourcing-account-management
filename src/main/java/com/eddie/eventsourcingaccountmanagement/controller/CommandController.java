package com.eddie.eventsourcingaccountmanagement.controller;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.exception.BalanceException;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import com.eddie.eventsourcingaccountmanagement.service.AggregateService;
import com.eddie.eventsourcingaccountmanagement.service.CommandService;
import com.eddie.eventsourcingaccountmanagement.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController {
    private AggregateService aggregateService;
    private CommandService commandService;
    private QueryService queryService;

    @Autowired
    public CommandController(AggregateService aggregateService, CommandService commandService, QueryService queryService){
        this.aggregateService = aggregateService;
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("/account")
    public Event createAccount(@RequestParam(value = "owner") String owner) {
        Aggregate aggregate = aggregateService.createAggregate();
        return commandService.createAccount(aggregate, owner);
    }

    @PostMapping("/account/{id}/deposit")
    public Event deposit(@PathVariable long id, @RequestParam(value = "amount") long amount) {
        Aggregate aggregate = aggregateService.getAggregate(id);
        return commandService.deposit(aggregate, amount);
    }

    @PostMapping("/account/{id}/withdrawal")
    public Event withdrawal(@PathVariable long id, @RequestParam(value = "amount") long amount) throws BalanceException {
        Aggregate aggregate = aggregateService.getAggregate(id);
        BankAccount account = queryService.getBankAccount(id);
        return commandService.withdrawal(aggregate, account, amount);
    }
}

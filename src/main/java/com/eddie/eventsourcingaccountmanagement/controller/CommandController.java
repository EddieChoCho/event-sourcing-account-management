package com.eddie.eventsourcingaccountmanagement.controller;

import com.eddie.eventsourcingaccountmanagement.command.CreateAccount;
import com.eddie.eventsourcingaccountmanagement.command.Deposit;
import com.eddie.eventsourcingaccountmanagement.command.Withdrawal;
import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.exception.BalanceException;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import com.eddie.eventsourcingaccountmanagement.service.AggregateService;
import com.eddie.eventsourcingaccountmanagement.service.CommandService;
import com.eddie.eventsourcingaccountmanagement.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command/")
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

    @PostMapping("createAccount")
    public Event createAccount(@RequestParam(value = "owner") String owner, @RequestParam(value = "money") long money) {
        Aggregate aggregate = aggregateService.createAggregate();
        CreateAccount command = new CreateAccount(owner, money);
        return commandService.applyCommand(aggregate, command);
    }

    @PostMapping("deposit")
    public Event deposit(@RequestParam(value = "id") long id, @RequestParam(value = "amount") long amount) {
        Aggregate aggregate = aggregateService.getAggregate(id);
        Deposit command = new Deposit(amount);
        return commandService.applyCommand(aggregate, command);
    }

    @PostMapping("withdrawal")
    public Event withdrawal(@RequestParam(value = "id") long id, @RequestParam(value = "amount") long amount) throws BalanceException {
        Aggregate aggregate = aggregateService.getAggregate(id);
        BankAccount account = queryService.getBankAccount(id);
        Withdrawal command = new Withdrawal(account, amount);
        return commandService.applyCommand(aggregate, command);
    }
}

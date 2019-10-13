package com.eddie.eventsourcingaccountmanagement.controller;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QueryController {
    private QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService){
        this.queryService = queryService;
    }

    @GetMapping("/account/{id}")
    public BankAccount withdrawal(@PathVariable long id) {
        return queryService.getBankAccount(id);
    }

}

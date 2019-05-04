package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.command.Command;
import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
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

    public Event applyCommand(Aggregate aggregate, Command command){
        AccountEvent event = command.apply();
        return eventService.saveEvent(event, aggregate);
    }

    public Event applyCommand(Aggregate aggregate, Command command, boolean isTest) throws InterruptedException {
        AccountEvent event = command.apply();
        return eventService.saveEvent(event, aggregate, isTest);
    }
}

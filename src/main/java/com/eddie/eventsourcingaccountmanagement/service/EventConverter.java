package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import com.eddie.eventsourcingaccountmanagement.model.enums.EventType;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class EventConverter {

    private static Gson gson = new Gson();

    public AccountEvent convertToAccountEvent(Event event){
        EventType type = EventType.valueOf(event.getType());
        Class<AccountEvent> eventClass = type.getEventClass();
        AccountEvent accountEvent = gson.fromJson(event.getContent(), eventClass);
        accountEvent.setVersion(event.getVersion());
        return accountEvent;
    }

    public Event convertToEvent(AccountEvent accountEvent, Aggregate aggregate){
        String eventType = accountEvent.getClass().getSimpleName();
        String jsonString = gson.toJson(accountEvent);
        return new Event(eventType, jsonString, aggregate);
    }
}

package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.exception.ConcurrencyException;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import com.eddie.eventsourcingaccountmanagement.repository.AggregateRepository;
import com.eddie.eventsourcingaccountmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private EventRepository eventRepository;
    private AggregateService aggregateService;
    private EventConverter converter;

    @Autowired
    public EventService(AggregateService aggregateService, EventRepository eventRepository, EventConverter converter){
        this.aggregateService = aggregateService;
        this.eventRepository = eventRepository;
        this.converter = converter;
    }

    public List<AccountEvent> getAccountEvents(long aggregateId){
        List<Event> events = eventRepository.findAllByAggregateIdOrderByVersion(aggregateId);
        return events.stream().map(event -> converter.convertToAccountEvent(event)).collect(Collectors.toList());
    }

    public Event saveEvent(AccountEvent accountEvent, Aggregate aggregate) throws ConcurrencyException {
        Aggregate aggregateFromDatabase = aggregateService.getAggregate(aggregate.getId());
        if(aggregate.getVersion() != aggregateFromDatabase.getVersion()){
            throw new ConcurrencyException();
        }
        Event event = converter.convertToEvent(accountEvent, aggregate);
        aggregateFromDatabase.setVersion(event.getVersion());
        eventRepository.save(event);
        aggregateService.save(aggregate);
        return event;
    }

}

package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.exception.ConcurrencyException;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import com.eddie.eventsourcingaccountmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

    private EventRepository eventRepository;
    private EventConverter converter;

    @Autowired
    public EventService(EventRepository eventRepository, EventConverter converter){
        this.eventRepository = eventRepository;
        this.converter = converter;
    }

    public List<AccountEvent> getAccountEvents(long aggregateId){
        List<Event> events = eventRepository.findAllByAggregateIdOrderByVersion(aggregateId);
        return events.stream().map(event -> converter.convertToAccountEvent(event)).collect(Collectors.toList());
    }

    public Event saveEvent(AccountEvent accountEvent, Aggregate aggregate) throws ConcurrencyException {
        Event event = converter.convertToEvent(accountEvent, aggregate);
        eventRepository.saveEvent(event);
        return event;
    }

}

package com.eddie.eventsourcingaccountmanagement.repository;

import com.eddie.eventsourcingaccountmanagement.model.Event;

public interface EventRepositoryCustom {
    void saveEvent(Event event, long aggregateId);
}

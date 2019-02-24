package com.eddie.eventsourcingaccountmanagement.repository;

import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;

public interface EventRepositoryCustom {
    void saveEvent(Event event, Aggregate aggregate);
}

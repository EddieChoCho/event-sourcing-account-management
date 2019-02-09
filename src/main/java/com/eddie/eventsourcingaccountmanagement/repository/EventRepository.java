package com.eddie.eventsourcingaccountmanagement.repository;

import com.eddie.eventsourcingaccountmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByAggregateIdOrderByVersion(long aggregateId);
}

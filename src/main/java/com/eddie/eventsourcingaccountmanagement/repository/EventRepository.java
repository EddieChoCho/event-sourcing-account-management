package com.eddie.eventsourcingaccountmanagement.repository;

import com.eddie.eventsourcingaccountmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends EventRepositoryCustom, JpaRepository<Event, Long> {
    List<Event> findAllByAggregateIdOrderByVersion(long aggregateId);

    List<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(long aggregateId, long version);
}

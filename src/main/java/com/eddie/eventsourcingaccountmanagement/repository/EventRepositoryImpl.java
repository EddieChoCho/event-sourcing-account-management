package com.eddie.eventsourcingaccountmanagement.repository;

import com.eddie.eventsourcingaccountmanagement.exception.ConcurrencyException;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class EventRepositoryImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEvent(Event event, Aggregate aggregate) {
        Query query = entityManager.createNativeQuery("SELECT version FROM aggregate WHERE id = :aggregateId;", long.class);
        query.setParameter("aggregateId", aggregate.getId());
        long aggregateVersion = (long) query.getSingleResult();
        if(aggregate.getVersion() != aggregateVersion){
            throw new ConcurrencyException();
        }
        Query insertEvent = entityManager.createNativeQuery("INSERT ");
        Query updateAggregate = entityManager.createNativeQuery("UPDATE aggregate SET version = :version WHERE id = :aggregateId;");
        updateAggregate.setParameter("aggregateId", aggregate.getId());
        updateAggregate.setParameter("version", event.getVersion());
        updateAggregate.executeUpdate();
    }
}

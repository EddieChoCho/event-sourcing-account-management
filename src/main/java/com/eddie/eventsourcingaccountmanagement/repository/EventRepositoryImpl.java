package com.eddie.eventsourcingaccountmanagement.repository;

import com.eddie.eventsourcingaccountmanagement.exception.ConcurrencyException;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class EventRepositoryImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEvent(Event event, long aggregateId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM aggregate WHERE id = :aggregateId", Aggregate.class);
        query.setParameter("aggregateId", aggregateId);
        Aggregate aggregate = (Aggregate) query.getSingleResult();
        if(aggregate.getVersion() != event.getAggregate().getVersion()){
            throw new ConcurrencyException();
        }
        entityManager.lock(aggregate, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        entityManager.persist(event);
    }
}

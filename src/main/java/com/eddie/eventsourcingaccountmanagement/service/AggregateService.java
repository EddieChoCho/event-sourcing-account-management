package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.repository.AggregateRepository;
import org.springframework.stereotype.Service;

@Service
public class AggregateService {
    private AggregateRepository aggregateRepository;

    public AggregateService(AggregateRepository aggregateRepository){
        this.aggregateRepository = aggregateRepository;
    }

    public Aggregate createAggregate(){
        Aggregate aggregate = new Aggregate();
        return aggregateRepository.save(aggregate);
    }

    public Aggregate getAggregate(long id){
        return aggregateRepository.getOne(id);
    }
}

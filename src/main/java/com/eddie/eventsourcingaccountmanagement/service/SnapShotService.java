package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccountSnapshot;
import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Snapshot;
import com.eddie.eventsourcingaccountmanagement.repository.AggregateRepository;
import com.eddie.eventsourcingaccountmanagement.repository.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SnapShotService {

    private AggregateRepository aggregateRepository;
    private EventService eventService;
    private SnapshotRepository snapshotRepository;
    private SnapshotConverter converter;

    @Autowired
    public SnapShotService(AggregateRepository aggregateRepository, EventService eventService, SnapshotRepository snapshotRepository, SnapshotConverter converter){
        this.aggregateRepository = aggregateRepository;
        this.eventService = eventService;
        this.snapshotRepository = snapshotRepository;
        this.converter = converter;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void createSnapshots(){
        List<Aggregate> aggregates = aggregateRepository.findAll();
        aggregates.forEach(aggregate -> {
            Optional<Snapshot> optionalSnapshot = snapshotRepository.findTopByAggregateIdOrderByVersionDesc(aggregate.getId());
            if(optionalSnapshot.isPresent()){
                Snapshot snapshot = optionalSnapshot.get();
                if(snapshot.getVersion() != aggregate.getVersion()){
                    BankAccountSnapshot accountSnapshot = this.getAccountSnapshot(aggregate.getId(), snapshot);
                    this.saveSnapshot(accountSnapshot, aggregate);
                }
            }else {
                BankAccountSnapshot accountSnapshot = this.getAccountSnapshot(aggregate.getId());
                this.saveSnapshot(accountSnapshot, aggregate);
            }
        });
    }

    public BankAccountSnapshot getSnapshot(long aggregateId){
        Optional<Snapshot> optionalSnapshot = snapshotRepository.findTopByAggregateIdOrderByVersionDesc(aggregateId);
        if(optionalSnapshot.isPresent()){
            Snapshot snapshot = optionalSnapshot.get();
            return this.getAccountSnapshot(aggregateId, snapshot);
        }else {
            return this.getAccountSnapshot(aggregateId);
        }
    }

    public Snapshot createSnapshot(Aggregate aggregate){
        BankAccountSnapshot accountSnapshot = this.getSnapshot(aggregate.getId());
        return this.saveSnapshot(accountSnapshot, aggregate);
    }

    private BankAccountSnapshot getAccountSnapshot(long aggregateId){
        BankAccountSnapshot snapshot = new BankAccountSnapshot(aggregateId);
        List<AccountEvent> events = eventService.getAccountEvents(aggregateId);
        events.forEach(snapshot::apply);
        return snapshot;
    }

    private BankAccountSnapshot getAccountSnapshot(long aggregateId, Snapshot snapshot){
        BankAccountSnapshot accountSnapshot = converter.convertToAccountSnapshot(snapshot);
        List<AccountEvent> events = eventService.getAccountEvents(aggregateId, snapshot.getVersion());
        events.forEach(accountSnapshot::apply);
        return accountSnapshot;
    }

    private Snapshot saveSnapshot(BankAccountSnapshot accountSnapshot, Aggregate aggregate) {
        Snapshot snapshot = converter.convertToSnapshot(accountSnapshot, aggregate);
        snapshotRepository.save(snapshot);
        return snapshot;
    }

}

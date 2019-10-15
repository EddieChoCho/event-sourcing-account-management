package com.eddie.eventsourcingaccountmanagement.repository;

import com.eddie.eventsourcingaccountmanagement.model.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {
    Optional<Snapshot> findTopByAggregateIdOrderByVersionDesc(long aggregateId);
}

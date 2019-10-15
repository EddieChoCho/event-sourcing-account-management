package com.eddie.eventsourcingaccountmanagement.service;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccountSnapshot;
import com.eddie.eventsourcingaccountmanagement.model.Aggregate;
import com.eddie.eventsourcingaccountmanagement.model.Snapshot;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class SnapshotConverter {

    private static Gson gson = new Gson();

    public BankAccountSnapshot convertToAccountSnapshot(Snapshot snapshot){
        BankAccountSnapshot accountSnapshot = gson.fromJson(snapshot.getContent(), BankAccountSnapshot.class);
        return accountSnapshot;
    }

    public Snapshot convertToSnapshot(BankAccountSnapshot accountSnapshot, Aggregate aggregate){
        String jsonString = gson.toJson(accountSnapshot);
        return new Snapshot(jsonString, aggregate);
    }
}

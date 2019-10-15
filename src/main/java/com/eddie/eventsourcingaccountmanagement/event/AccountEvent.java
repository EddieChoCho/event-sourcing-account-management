package com.eddie.eventsourcingaccountmanagement.event;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import com.eddie.eventsourcingaccountmanagement.dom.BankAccountSnapshot;
import lombok.Data;

@Data
public abstract class AccountEvent {

     private long version;

     public abstract BankAccount apply(BankAccount account);

     public abstract BankAccountSnapshot apply(BankAccountSnapshot snapshot);
}

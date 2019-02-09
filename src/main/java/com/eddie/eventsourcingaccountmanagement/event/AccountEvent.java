package com.eddie.eventsourcingaccountmanagement.event;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;

public interface AccountEvent {

     BankAccount apply(BankAccount account);
}

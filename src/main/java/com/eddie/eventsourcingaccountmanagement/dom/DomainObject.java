package com.eddie.eventsourcingaccountmanagement.dom;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.event.BankAccountCreated;
import com.eddie.eventsourcingaccountmanagement.event.DepositPerformed;
import com.eddie.eventsourcingaccountmanagement.event.WithdrawalPerformed;

public interface DomainObject {

    default void apply(final AccountEvent event){
        if(event instanceof BankAccountCreated){
            this.apply((BankAccountCreated) event);
        } else if(event instanceof DepositPerformed){
            this.apply((DepositPerformed) event);
        } else if(event instanceof WithdrawalPerformed){
            this.apply((WithdrawalPerformed) event);
        }
    }

    void apply(BankAccountCreated event);

    void apply(DepositPerformed event);

    void apply(WithdrawalPerformed event);
}

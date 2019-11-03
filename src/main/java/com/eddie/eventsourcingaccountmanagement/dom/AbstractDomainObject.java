package com.eddie.eventsourcingaccountmanagement.dom;

import com.eddie.eventsourcingaccountmanagement.event.AccountEvent;
import com.eddie.eventsourcingaccountmanagement.event.BankAccountCreated;
import com.eddie.eventsourcingaccountmanagement.event.DepositPerformed;
import com.eddie.eventsourcingaccountmanagement.event.WithdrawalPerformed;

public abstract class AbstractDomainObject {

    public void apply(final AccountEvent event){
        if(event instanceof BankAccountCreated){
            this.apply((BankAccountCreated) event);
        } else if(event instanceof DepositPerformed){
            this.apply((DepositPerformed) event);
        } else if(event instanceof WithdrawalPerformed){
            this.apply((WithdrawalPerformed) event);
        }
    }

    abstract public void apply(BankAccountCreated event);

    abstract public void apply(DepositPerformed event);

    abstract public void apply(WithdrawalPerformed event);
}

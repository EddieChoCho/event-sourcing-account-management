package com.eddie.eventsourcingaccountmanagement.dom;

import com.eddie.eventsourcingaccountmanagement.event.BankAccountCreated;
import com.eddie.eventsourcingaccountmanagement.event.DepositPerformed;
import com.eddie.eventsourcingaccountmanagement.event.WithdrawalPerformed;
import lombok.Getter;

@Getter
public class BankAccountSnapshot implements DomainObject {

    private Long id;
    private String owner;
    private long balance;
    private int depositTimes;
    private long totalDepositAmount;
    private int withdrawalTimes;
    private long totalWithdrawalAmount;
    private long version;

    public BankAccountSnapshot(long id){
        this.id = id;
    }

    public void apply(BankAccountCreated event) {
        this.owner = event.getOwner();
        this.version++;
    }

    public void apply(DepositPerformed event) {
        this.balance += event.getAmount();
        this.totalDepositAmount += event.getAmount();
        this.depositTimes++;
        this.version++;
    }

    public void apply(WithdrawalPerformed event) {
        this.balance -= event.getAmount();
        this.totalWithdrawalAmount -= event.getAmount();
        this.withdrawalTimes++;
        this.version++;
    }

}

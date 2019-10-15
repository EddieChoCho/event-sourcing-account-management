package com.eddie.eventsourcingaccountmanagement.dom;

import lombok.Data;

@Data
public class BankAccountSnapshot {

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

    public void deposit(long amount){
        this.balance += amount;
        this.totalDepositAmount += amount;
        this.depositTimes++;
    }

    public void withdrawal(long amount){
        this.balance -= amount;
        this.totalWithdrawalAmount -= amount;
        this.withdrawalTimes++;
    }

}

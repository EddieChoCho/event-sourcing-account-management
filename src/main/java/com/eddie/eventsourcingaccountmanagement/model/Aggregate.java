package com.eddie.eventsourcingaccountmanagement.model;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Aggregate extends AbstractEntity{
    private String type = BankAccount.class.getSimpleName();
    private long version;
}

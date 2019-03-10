package com.eddie.eventsourcingaccountmanagement.model;

import com.eddie.eventsourcingaccountmanagement.dom.BankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
@Data
@NoArgsConstructor
public class Aggregate extends AbstractEntity{
    private String type = BankAccount.class.getSimpleName();
    @Version
    private long version;
}

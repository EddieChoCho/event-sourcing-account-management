package com.eddie.eventsourcingaccountmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Event extends AbstractEntity {
    private String type;
    private String content;
    private long version;
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Aggregate aggregate;

    public Event(String type, String content, Aggregate aggregate){
        this.type = type;
        this.content = content;
        this.aggregate = aggregate;
        this.version = aggregate.getVersion() + 1;
    }

}

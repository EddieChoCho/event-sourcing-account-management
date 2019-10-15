package com.eddie.eventsourcingaccountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EventSourcingAccountManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingAccountManagementApplication.class, args);
    }

}


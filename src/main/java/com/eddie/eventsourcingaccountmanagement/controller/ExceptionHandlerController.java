package com.eddie.eventsourcingaccountmanagement.controller;

import com.eddie.eventsourcingaccountmanagement.exception.BalanceException;
import org.hibernate.StaleObjectStateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BalanceException.class)
    @ResponseBody
    public String handleCustomException(BalanceException exception) {
        return exception.getLocalizedMessage();
    }

    @ExceptionHandler(StaleObjectStateException.class)
    @ResponseBody
    public String handleCustomException(StaleObjectStateException exception, Locale locale) {
        return exception.getLocalizedMessage();
    }
}

package com.company.bpmcourse.app;

import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.now;

@Component(value = "greetingMessageBean")
public class GreetingMessageBean {
    private static final Logger log = LoggerFactory.getLogger(GreetingMessageBean.class);

    public LocalTime printMessage(String message,
                                  DelegateExecution execution) {
        log.info("Service task: {}", execution.getCurrentActivityId());
        log.info( "{}", message);
        return now();
    }

    public void printTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = time.format(formatter);
        log.info("Time is: {}", formattedTime);
    }
}
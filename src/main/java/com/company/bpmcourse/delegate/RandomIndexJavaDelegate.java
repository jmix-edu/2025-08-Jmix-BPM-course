package com.company.bpmcourse.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomIndexJavaDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(RandomIndexJavaDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        long numberOfUsers = (long) execution.getVariable("numberOfUsers");
        long randomIndex = new Random().nextLong(numberOfUsers);

        execution.setVariable("randomIndex", randomIndex);

        log.info("Service task: {}, randomIndex: {}", execution.getCurrentActivityId(), randomIndex);
    }
}
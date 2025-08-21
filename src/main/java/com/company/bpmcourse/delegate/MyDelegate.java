package com.company.bpmcourse.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(MyDelegate.class);
    private int counter;
    public MyDelegate() {
        log.info("Instance created: {} with hash: {}", this, this.hashCode());
    }

    @Override
    public void execute(DelegateExecution execution) {
        log.info("My delegate executed: hash: {}, activity ID {}, counter: {}", this.hashCode(), execution.getCurrentActivityId(), counter++);
    }
}
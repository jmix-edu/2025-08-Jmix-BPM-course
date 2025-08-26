package com.company.bpmcourse.app;

import org.flowable.engine.delegate.BpmnError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component(value = "errorService")
public class ErrorService {

    private static final int FAIL_PROBABILITY = 50;
    private static final Logger log = LoggerFactory.getLogger(ErrorService.class);

    public void probablyError() {
        if (failure()) {
            log.info("Error occurred!");
            throw new BpmnError("Random error");
        } else {
            log.info("Success!");
        }

    }

    private boolean failure() {
        return new Random().nextInt(100) < FAIL_PROBABILITY;
    }

    public void techError(String code, Boolean error) {
        if (error != null && !error) {
            return;
        } else {
            log.info("Technical error: {}", code);
            throw new RuntimeException(code);
        }
    }

}
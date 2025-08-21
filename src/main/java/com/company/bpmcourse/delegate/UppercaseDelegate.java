package com.company.bpmcourse.delegate;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UppercaseDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(UppercaseDelegate.class);
    private Expression messageField;
    private Expression decorationField;

    @Override
    public void execute(DelegateExecution execution) {
        String message = (String) messageField.getValue(execution);
        String decoration = (String) decorationField.getValue(execution);

        String uppercaseMessage = message.toUpperCase();
        String result = decoration + uppercaseMessage + decoration;

        execution.setVariable("message", result);

        log.info("Service task with fields: {}, resulting message: {}", execution.getCurrentActivityId(), result);
    }
}
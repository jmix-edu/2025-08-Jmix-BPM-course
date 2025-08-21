package com.company.bpmcourse.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApproveTaskAssignedListener implements TaskListener {
    private static final Logger log = LoggerFactory.getLogger(ApproveTaskAssignedListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        String assignee = delegateTask.getAssignee();
        delegateTask.setVariable("approver", assignee);
        log.info("Task listener - assignee: {}", assignee);
    }
}

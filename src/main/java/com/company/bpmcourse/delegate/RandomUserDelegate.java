package com.company.bpmcourse.delegate;

import com.company.bpmcourse.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.hsqldb.scriptio.ScriptReaderText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomUserDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(RandomUserDelegate.class);
    private final SystemAuthenticator systemAuthenticator;
    private final DataManager dataManager;

    public RandomUserDelegate(DataManager dataManager, SystemAuthenticator systemAuthenticator) {
        this.dataManager = dataManager;
        this.systemAuthenticator = systemAuthenticator;
    }

    @Override
    public void execute(DelegateExecution execution) {
        List<User> userList;

        Long randomIndex = (Long) execution.getVariable("randomIndex");
        systemAuthenticator.begin();
        try {
            userList = dataManager.load(User.class).all().list();
        } finally {
            systemAuthenticator.end();
        }

        String username = userList.get(Math.toIntExact(randomIndex)).getUsername().toUpperCase();
        String message = (String) execution.getVariable("message");
        String result = username + ", " + message;

        execution.setVariable("message", result);

        log.info("Service task: {}, username: {}", execution.getCurrentActivityId(), username);

    }


}
package com.company.bpmcourse.provider;

import com.company.bpmcourse.entity.User;
import io.jmix.bpm.provider.UserProvider;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@UserProvider(value = "randomRobinAccountantProvider")
public class RandomRobinAccountantProvider {

    private static final Logger log = LoggerFactory.getLogger(RandomRobinAccountantProvider.class);
    @Autowired
    private DataManager dataManager;

    @Autowired
    private SystemAuthenticator authenticator;

    /**
     * This method returns username
     */
    public String getUser() {
        authenticator.begin();

        try {
            List<User> accountants = dataManager.load(User.class)
                    .query("select u from User u where u.position = 'accountant'")
                    .list();

            int randomNumber = new Random().nextInt(accountants.size());
            String accountantUsername = accountants.get(randomNumber).getUsername();
            log.info("Selected accountant: {}", accountantUsername );
            return accountantUsername;
        } finally {
            authenticator.end();
        }
    }
}
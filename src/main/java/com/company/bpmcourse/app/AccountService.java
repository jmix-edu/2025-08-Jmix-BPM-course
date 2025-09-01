package com.company.bpmcourse.app;

import com.company.bpmcourse.entity.Account;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.Thread.sleep;

@Component
public class AccountService {

    public static final int MAX_DELAY = 3000;
    public static final int FAIL_PROBABILITY = 30;
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final DataManager dataManager;
    private final SystemAuthenticator systemAuthenticator;

    public AccountService(DataManager dataManager, SystemAuthenticator systemAuthenticator) {
        this.dataManager = dataManager;
        this.systemAuthenticator = systemAuthenticator;
    }

    public boolean debit(String owner, long amountToDebit) {
        log.info("Owner - debit: {}", owner);
        delay();

        if (failure()) {
            return false;
        }

        systemAuthenticator.begin();
        try {
            Account account = dataManager.load(Account.class)
                    .query("e.owner = :owner")
                    .parameter("owner", owner)
                    .one();
            long newAmount = account.getAmount() - amountToDebit;
            if (newAmount < 0) {
                log.info("ERROR: Can not debit account {}", owner);
                return false;
            } else {
                account.setAmount(newAmount);
                dataManager.save(account);
                log.info("SUCCESS: Debit account {} by {}", owner, amountToDebit);
                return true;
            }
        } finally {
            systemAuthenticator.end();
        }

    }

    public boolean credit(String owner, long amountToCredit) {
        log.info("Owner - credit: {}", owner);
        delay();
        if (failure()) {
            return false;
        }

        systemAuthenticator.begin();
        try {
            Account account = dataManager.load(Account.class)
                    .query("e.owner = :owner")
                    .parameter("owner", owner)
                    .one();
            long newAmount = account.getAmount() + amountToCredit;
            if (newAmount > 1000000L) {
                log.info("ERROR: Can not credit account {}", owner);
                return false;
            } else {
                account.setAmount(newAmount);
                dataManager.save(account);
                log.info("SUCCESS: Credit account {} by {}", owner, amountToCredit);
                return true;
            }
        } finally {
            systemAuthenticator.end();
        }
    }

    private void delay() {
        Random random = new Random();
        int millis = random.nextInt(MAX_DELAY);
        try {
            sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    private boolean failure() {
        Random random = new Random();
        return random.nextInt(100) < FAIL_PROBABILITY;
    }

}
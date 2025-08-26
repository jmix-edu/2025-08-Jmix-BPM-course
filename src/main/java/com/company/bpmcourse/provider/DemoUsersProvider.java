package com.company.bpmcourse.provider;

import com.company.bpmcourse.entity.User;
import io.jmix.bpm.provider.UserListProvider;
import io.jmix.core.UnconstrainedDataManager;

import java.util.Collections;
import java.util.List;

@UserListProvider(value = "demoUsersProvider")
public class DemoUsersProvider {

    private final UnconstrainedDataManager unconstrainedDataManager;

    public DemoUsersProvider(UnconstrainedDataManager unconstrainedDataManager) {
        this.unconstrainedDataManager = unconstrainedDataManager;
    }

    /**
     * This method returns list of usernames
     */
    public List<String> getUsers() {
        return unconstrainedDataManager.load(User.class)
                .all()
                .list().stream()
                .map(User::getDisplayName)
                .toList();
    }
}
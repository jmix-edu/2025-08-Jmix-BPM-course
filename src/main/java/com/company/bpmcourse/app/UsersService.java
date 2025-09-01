package com.company.bpmcourse.app;

import com.company.bpmcourse.entity.User;
import io.jmix.core.UnconstrainedDataManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "usersService")
public class UsersService {

    private final UnconstrainedDataManager unconstrainedDataManager;

    public UsersService(UnconstrainedDataManager unconstrainedDataManager) {
        this.unconstrainedDataManager = unconstrainedDataManager;
    }

    public List<User> loadDevelopers() {

        return unconstrainedDataManager.load(User.class)
                .query("e.position = 'developer'")
                .list();
    }
}
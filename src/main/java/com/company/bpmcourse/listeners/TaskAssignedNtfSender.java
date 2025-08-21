package com.company.bpmcourse.listeners;

import com.company.bpmcourse.entity.User;
import io.jmix.bpm.engine.events.UserTaskAssignedEvent;
import io.jmix.core.DataManager;
import io.jmix.notifications.NotificationManager;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskAssignedNtfSender {

    @Autowired
    private DataManager dataManager;

    @Autowired
    private NotificationManager notificationManager;

    @EventListener
    public void onTaskAssigned(UserTaskAssignedEvent event) {
        User user = dataManager.load(User.class)
                .query(" e.username = :username")
                .parameter("username", event.getUsername())
                .one();

        Task task = event.getTask();

        notificationManager.createNotification()
                .withSubject("New task")
                .withRecipients(user)
                .toChannelsByNames("in-app")
                .withPlainTextContentType()
                .withTypeName("task")
                .withBody(
                        "A new tak [" + task.getName() + "] is assigned to you"
                )
                .send();
    }
}

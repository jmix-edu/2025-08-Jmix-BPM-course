package com.company.bpmcourse.app;

import com.company.bpmcourse.entity.PizzaItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jmix.core.EntityStates;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.flowui.UiEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PizzaItemsService {
    private static final Logger log = LoggerFactory.getLogger(PizzaItemsService.class);
    private final EntityStates entityStates;
    private final SystemAuthenticator systemAuthenticator;
    private final UiEventPublisher uiEventPublisher;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public PizzaItemsService(EntityStates entityStates, SystemAuthenticator systemAuthenticator, UiEventPublisher uiEventPublisher) {
        this.entityStates = entityStates;
        this.systemAuthenticator = systemAuthenticator;
        this.uiEventPublisher = uiEventPublisher;
    }

    public void requestPizzas() {
        kafkaTemplate.send("requests", "items_request");
    }

    @KafkaListener(topics = "pizza_items", groupId = "my_group")
    public void listen(String message) throws JsonProcessingException {
        log.info("Message in consumer: {}", message);

        ObjectMapper mapper = new ObjectMapper();
        List<PizzaItem> items = mapper.readerForListOf(PizzaItem.class).readValue(message);
        log.info("Collection size is: {}", items.size());
        for (PizzaItem item : items) {
            entityStates.setNew(item, false);
        }

        try {
            String username = "admin";
            systemAuthenticator.runWithUser(username,() -> publishEvent(items, username));
            log.info("Username is: {}", username);
        } finally {
            systemAuthenticator.end();
        }

    }

    private void publishEvent(List<PizzaItem> items, String username) {
        uiEventPublisher.publishEventForUsers(new PizzaItemsReceivedEvent(this, items)
                , Collections.singleton(username));
    }
}




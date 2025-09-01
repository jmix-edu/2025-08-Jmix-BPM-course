package com.company.bpmcourse.app;

import com.company.bpmcourse.entity.PizzaItem;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class PizzaItemsReceivedEvent extends ApplicationEvent {
    private final List<PizzaItem> items;

    public PizzaItemsReceivedEvent(Object source, List<PizzaItem> items) {
        super(source);
        this.items = items;
    }

    public List<PizzaItem> getItems() {
        return items;
    }
}

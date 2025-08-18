package com.company.bpmcourse.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum PizzaItem implements EnumClass<String> {

    PEPPERONI("A"),
    FOUR_CHEESE("B"),
    VEGETABLE("C");

    private final String id;

    PizzaItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static PizzaItem fromId(String id) {
        for (PizzaItem at : PizzaItem.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
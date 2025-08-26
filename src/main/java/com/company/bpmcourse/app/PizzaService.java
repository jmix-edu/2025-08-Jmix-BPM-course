package com.company.bpmcourse.app;

import com.company.bpmcourse.entity.PizzaItem;
import io.jmix.core.UnconstrainedDataManager;
import org.springframework.stereotype.Component;

@Component(value = "pizzaService")
public class PizzaService {

    private final UnconstrainedDataManager unconstrainedDataManager;

    public PizzaService(UnconstrainedDataManager unconstrainedDataManager) {
        this.unconstrainedDataManager = unconstrainedDataManager;
    }

    public long changePrice(PizzaItem pizzaItem, long newPrice) {
        long oldPrice = pizzaItem.getPrice();
        pizzaItem.setPrice(newPrice);
        unconstrainedDataManager.save(pizzaItem);
        return oldPrice;
    }

    public void undoPriceChange(PizzaItem pizzaItem, long oldPrice) {
        pizzaItem.setPrice(oldPrice);
        unconstrainedDataManager.save(pizzaItem);
    }

}
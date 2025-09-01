package com.company.bpmcourse.app;

import com.company.bpmcourse.entity.OrderLine;
import com.company.bpmcourse.entity.PizzaItem;
import com.company.bpmcourse.entity.PizzaOrder;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.UnconstrainedDataManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "pizzaService")
public class PizzaService {

    private final UnconstrainedDataManager unconstrainedDataManager;
    private final FetchPlans fetchPlans;

    public PizzaService(UnconstrainedDataManager unconstrainedDataManager, FetchPlans fetchPlans) {
        this.unconstrainedDataManager = unconstrainedDataManager;
        this.fetchPlans = fetchPlans;
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

    public List<PizzaItem> getPizzaItems() {
        return unconstrainedDataManager.load(PizzaItem.class)
                .all()
                .fetchPlan(createPlnWithRecipe())
                .list();
    }

    private FetchPlan createPlnWithRecipe() {
        return fetchPlans.builder(PizzaItem.class)
                .addFetchPlan(FetchPlan.BASE)
                .add("recipe", fetchPlanBuilder -> {
                    fetchPlanBuilder.addFetchPlan(FetchPlan.BASE);
                })
                .build();
    }

    public long calculateOrder(PizzaOrder order) {

        long amount = 0L;

        for (OrderLine line : order.getOrderLines()) {
            amount += line.getPizzaItem().getPrice();
        }

        return amount;

    }

}
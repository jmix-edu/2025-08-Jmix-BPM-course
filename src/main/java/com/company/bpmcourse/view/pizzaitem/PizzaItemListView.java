package com.company.bpmcourse.view.pizzaitem;

import com.company.bpmcourse.app.PizzaItemsReceivedEvent;
import com.company.bpmcourse.app.PizzaItemsService;
import com.company.bpmcourse.entity.PizzaItem;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import java.util.List;

@Route(value = "pizza-items", layout = MainView.class)
@ViewController(id = "PizzaItem.list")
@ViewDescriptor(path = "pizza-item-list-view.xml")
@LookupComponent("pizzaItemsDataGrid")
@DialogMode(width = "50em")
public class PizzaItemListView extends StandardListView<PizzaItem> {
    @Autowired
    private PizzaItemsService pizzaItemsService;
    @ViewComponent
    private CollectionContainer<PizzaItem> pizzaItemsDc;

    @Subscribe
    public void onInit(final InitEvent event) {
        pizzaItemsService.requestPizzas();
    }

    @Subscribe("pizzaItemsDataGrid.refreshAction")
    public void onPizzaItemsDataGridRefreshAction(final ActionPerformedEvent event) {
        pizzaItemsService.requestPizzas();
    }

    @EventListener
    public void onPizzaItemsReceived(PizzaItemsReceivedEvent event) {
        pizzaItemsDc.setItems(event.getItems());
    }
}

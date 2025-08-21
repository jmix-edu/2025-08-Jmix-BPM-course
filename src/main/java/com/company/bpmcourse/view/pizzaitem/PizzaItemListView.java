package com.company.bpmcourse.view.pizzaitem;

import com.company.bpmcourse.entity.PizzaItem;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "pizza-items", layout = MainView.class)
@ViewController(id = "PizzaItem.list")
@ViewDescriptor(path = "pizza-item-list-view.xml")
@LookupComponent("pizzaItemsDataGrid")
@DialogMode(width = "64em")
public class PizzaItemListView extends StandardListView<PizzaItem> {
}
package com.company.bpmcourse.view.pizzaorder;

import com.company.bpmcourse.entity.PizzaOrder;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "pizza-orders", layout = MainView.class)
@ViewController(id = "PizzaOrder.list")
@ViewDescriptor(path = "pizza-order-list-view.xml")
@LookupComponent("pizzaOrdersDataGrid")
@DialogMode(width = "64em")
public class PizzaOrderListView extends StandardListView<PizzaOrder> {
}
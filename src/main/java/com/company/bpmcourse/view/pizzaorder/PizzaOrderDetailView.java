package com.company.bpmcourse.view.pizzaorder;

import com.company.bpmcourse.entity.PizzaOrder;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "pizza-orders/:id", layout = MainView.class)
@ViewController(id = "PizzaOrder.detail")
@ViewDescriptor(path = "pizza-order-detail-view.xml")
@EditedEntityContainer("pizzaOrderDc")
public class PizzaOrderDetailView extends StandardDetailView<PizzaOrder> {
}
package com.company.bpmcourse.view.orderline;

import com.company.bpmcourse.entity.OrderLine;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "order-lines/:id", layout = MainView.class)
@ViewController(id = "OrderLine.detail")
@ViewDescriptor(path = "order-line-detail-view.xml")
@EditedEntityContainer("orderLineDc")
public class OrderLineDetailView extends StandardDetailView<OrderLine> {
}
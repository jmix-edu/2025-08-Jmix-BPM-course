package com.company.bpmcourse.view.orderline;

import com.company.bpmcourse.entity.OrderLine;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "order-lines", layout = MainView.class)
@ViewController(id = "OrderLine.list")
@ViewDescriptor(path = "order-line-list-view.xml")
@LookupComponent("orderLinesDataGrid")
@DialogMode(width = "64em")
public class OrderLineListView extends StandardListView<OrderLine> {
}
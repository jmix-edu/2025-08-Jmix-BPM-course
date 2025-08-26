package com.company.bpmcourse.view.placeorderform;


import com.company.bpmcourse.entity.PizzaItem;
import com.company.bpmcourse.entity.User;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.Outcome;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outcomes = {
        @Outcome(id = "submit")
}, outputVariables = {
        @OutputVariable(name = "approver", type = User.class),
        @OutputVariable(name = "approverMessage", type = String.class),
        @OutputVariable(name = "deliveryNumber", type = String.class),
        @OutputVariable(name = "pizzaEater", type = User.class),
        @OutputVariable(name = "pizzaItem", type = PizzaItem.class),
        @OutputVariable(name = "specialRequirements", type = String.class)
})
@Route(value = "place-order-form", layout = MainView.class)
@ViewController(id = "PlaceOrderForm")
@ViewDescriptor(path = "place-order-form.xml")
public class PlaceOrderForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "approver")
    @ViewComponent
    private EntityPicker<User> approverField;
    @ProcessVariable(name = "approverMessage")
    @ViewComponent
    private TypedTextField<String> approverMessageField;
    @ProcessVariable(name = "deliveryNumber")
    @ViewComponent
    private TypedTextField<String> deliveryNumberField;
    @ProcessVariable(name = "pizzaEater")
    @ViewComponent
    private EntityPicker<User> pizzaEaterField;
    @ProcessVariable(name = "pizzaItem")
    @ViewComponent
    private EntityPicker<PizzaItem> pizzaItemField;
    @ProcessVariable(name = "specialRequirements")
    @ViewComponent
    private TypedTextField<String> specialRequirementsField;

    @Subscribe(id = "submitBtn", subject = "clickListener")
    protected void onSubmitBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.taskCompletion()
                .withOutcome("submit")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
}
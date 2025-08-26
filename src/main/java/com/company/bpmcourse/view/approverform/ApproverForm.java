package com.company.bpmcourse.view.approverform;


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
        @Outcome(id = "approved"),
        @Outcome(id = "rejected")
}, outputVariables = {
        @OutputVariable(name = "approverMessage", type = String.class),
        @OutputVariable(name = "pizzaEater", type = User.class),
        @OutputVariable(name = "pizzaItem", type = PizzaItem.class)
})
@Route(value = "approver-form", layout = MainView.class)
@ViewController(id = "ApproverForm")
@ViewDescriptor(path = "approver-form.xml")
public class ApproverForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "approverMessage")
    @ViewComponent
    private TypedTextField<String> approverMessageField;
    @ProcessVariable(name = "pizzaEater")
    @ViewComponent
    private EntityPicker<User> pizzaEaterField;
    @ProcessVariable(name = "pizzaItem")
    @ViewComponent
    private EntityPicker<PizzaItem> pizzaItemField;

    @Subscribe(id = "approvedBtn", subject = "clickListener")
    protected void onApprovedBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.taskCompletion()
                .withOutcome("approved")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }

    @Subscribe(id = "rejectedBtn", subject = "clickListener")
    protected void onRejectedBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.taskCompletion()
                .withOutcome("rejected")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
}
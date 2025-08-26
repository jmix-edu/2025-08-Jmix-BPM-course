package com.company.bpmcourse.view.pizzaeaterform;


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
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outcomes = {
        @Outcome(id = "pizza_selected"),
        @Outcome(id = "not_hungry")
}, outputVariables = {
        @OutputVariable(name = "message", type = String.class),
        @OutputVariable(name = "pizzaItem", type = PizzaItem.class),
        @OutputVariable(name = "specialRequirements", type = String.class)
})
@Route(value = "pizza-eater-form", layout = MainView.class)
@ViewController(id = "PizzaEaterForm")
@ViewDescriptor(path = "pizza-eater-form.xml")
public class PizzaEaterForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "message")
    @ViewComponent
    private TypedTextField<String> messageField;
    @ProcessVariable(name = "pizzaItem")
    @ViewComponent
    private EntityComboBox<User> pizzaItemField;

    @ProcessVariable(name = "specialRequirements")
    @ViewComponent
    private JmixTextArea specialRequirementsField;

    @Subscribe(id = "pizza_selectedBtn", subject = "clickListener")
    protected void onPizza_selectedBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.taskCompletion()
                .withOutcome("pizza_selected")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }

    @Subscribe(id = "not_hungryBtn", subject = "clickListener")
    protected void onNot_hungryBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.taskCompletion()
                .withOutcome("not_hungry")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
}
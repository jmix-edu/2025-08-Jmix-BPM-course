package com.company.bpmcourse.view.pizzastartform;


import com.company.bpmcourse.entity.User;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outputVariables = {
        @OutputVariable(name = "message", type = String.class),
        @OutputVariable(name = "pizzaEater", type = User.class)
})
@Route(value = "pizza-start-form", layout = MainView.class)
@ViewController(id = "PizzaStartForm")
@ViewDescriptor(path = "pizza-start-form.xml")
public class PizzaStartForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "message")
    @ViewComponent
    private TypedTextField<String> messageField;
    @ProcessVariable(name = "pizzaEater")
    @ViewComponent
    private EntityComboBox<User> pizzaEaterField;
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "startProcessBtn", subject = "clickListener")
    protected void onStartProcessBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.processStarting()
                .saveInjectedProcessVariables()
                .start();
        closeWithDefaultAction();
    }

    @Subscribe(id = "descriptionButton", subject = "clickListener")
    public void onDescriptionButtonClick(final ClickEvent<JmixButton> event) {
        notifications.show("Select developer to feed and click start button");
    }

}
package com.company.bpmcourse.view.startdemoform;


import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outputVariables = {
        @OutputVariable(name = "message", type = String.class)
})
@Route(value = "start-demo-form", layout = MainView.class)
@ViewController(id = "StartDemoForm")
@ViewDescriptor(path = "start-demo-form.xml")
public class StartDemoForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;

    @ProcessVariable(name = "message")
    @ViewComponent
    private TypedTextField<String> messageField;

    @Subscribe(id = "startProcessBtn", subject = "clickListener")
    protected void onStartProcessBtnClick(ClickEvent<JmixButton> event) {
        processFormContext.processStarting()
                .saveInjectedProcessVariables()
                .start();
        closeWithDefaultAction();
    }
}
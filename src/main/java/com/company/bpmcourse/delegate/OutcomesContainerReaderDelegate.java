package com.company.bpmcourse.delegate;

import io.jmix.bpm.data.outcome.Outcome;
import io.jmix.bpm.data.outcome.OutcomesContainer;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OutcomesContainerReaderDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        OutcomesContainer result = (OutcomesContainer) execution.getVariable("Activity_approve_result");

        List<Outcome> outcomes = result.getOutcomes();
        StringBuilder sb = new StringBuilder();

        for (Outcome outcome : outcomes) {
            sb.append(outcome.getUser()).append(" - ")
                    .append(outcome.getOutcomeId()).append(" - ")
                    .append(outcome.getDate()).append("\n");
        }

        Map<String, Object> variables = execution.getVariables();
        Map<String, Object> variablesLocal = execution.getVariablesLocal();

        execution.setVariable("outcomes", sb.toString());
    }
}

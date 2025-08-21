package com.company.bpmcourse.listeners;

import io.jmix.bpm.engine.events.ProcessCompletedEvent;
import io.jmix.bpm.engine.events.ProcessStartedEvent;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventProcessListener{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(EventProcessListener.class);

    @EventListener
    public void onProcessStart(ProcessStartedEvent event){
        String key = event.getProcessDefinition().getKey();
        log.info("Process started: {}", key);
    }

    @EventListener
    public void onProcessCompleted(ProcessCompletedEvent event){
        String key = event.getProcessDefinition().getKey();
        log.info("Process completed: {}", key);
    }

}

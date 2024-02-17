package com.example.user.profile.library.temporal;

import com.example.user.profile.library.temporal.config.WorkerConfig;
import com.example.user.profile.library.temporal.worker.Worker;
import com.example.user.profile.library.temporal.workflow.TemporalWorkflow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemporalWorkflowFactory {
    private final Map<Class<? extends TemporalWorkflow>, ? extends Worker> workerMap;
    private final WorkerConfig workerConfig;

    public <T extends TemporalWorkflow> T newWorkflow(Class<T> workflowCls) {
        workerConfig.startFactory();
        return workerMap.get(workflowCls).newWorkflow(workflowCls);
    }
}

package com.samsepiol.bom.library.temporal;

import com.samsepiol.bom.library.temporal.worker.Worker;
import com.samsepiol.bom.library.temporal.workflow.TemporalWorkflow;
import com.samsepiol.bom.library.temporal.config.WorkerConfig;
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

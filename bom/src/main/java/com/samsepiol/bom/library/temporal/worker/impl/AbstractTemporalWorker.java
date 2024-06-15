package com.samsepiol.bom.library.temporal.worker.impl;

import com.samsepiol.bom.library.temporal.activity.TemporalActivity;
import com.samsepiol.bom.library.temporal.client.TemporalClient;
import com.samsepiol.bom.library.temporal.worker.Worker;
import com.samsepiol.bom.library.temporal.workflow.TemporalWorkflow;
import io.temporal.client.WorkflowOptions;
import io.temporal.common.RetryOptions;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractTemporalWorker implements Worker {
    private final Set<Class<? extends TemporalWorkflow>> WORKFLOW_IMPL_CLASSES;
    private final String TASK_QUEUE;
    private final Set<? extends TemporalActivity> ACTIVITY_IMPLEMENTATIONS;
    private final Map<Class<? extends TemporalWorkflow>, WorkflowOptions> WORKFLOW_OPTIONS_MAP;
    protected final TemporalClient temporalClient;

    protected AbstractTemporalWorker(Class<? extends TemporalWorkflow> workFlowImplClass,
                                     String taskQueue,
                                     TemporalActivity activityImplClass,
                                     WorkflowOptions workflowOptions,
                                     TemporalClient temporalClient) {
        this(Set.of(workFlowImplClass),
                taskQueue,
                Set.of(activityImplClass),
                Map.of((Class<? extends TemporalWorkflow>) (workFlowImplClass.getGenericInterfaces()[0]), workflowOptions),
                temporalClient);
    }

    @Override
    public String getTaskQueue() {
        return TASK_QUEUE;
    }

    @Override
    public Set<Class<? extends TemporalWorkflow>> getWorkflowImplementationTypes() {
        return WORKFLOW_IMPL_CLASSES;
    }

    @Override
    public Set<? extends TemporalActivity> getActivityImplementations() {
        return ACTIVITY_IMPLEMENTATIONS;
    }

    @Override
    public <T extends TemporalWorkflow> T newWorkflow(Class<T> cls) {
        return temporalClient.newWorkflow(cls, WorkflowOptions.newBuilder()
                .setTaskQueue(TASK_QUEUE)
                .setWorkflowId(Objects.requireNonNullElse(WORKFLOW_OPTIONS_MAP.get(cls).getWorkflowId(), UUID.randomUUID().toString()))
                .setRetryOptions(RetryOptions.getDefaultInstance())
                .build());
    }

}

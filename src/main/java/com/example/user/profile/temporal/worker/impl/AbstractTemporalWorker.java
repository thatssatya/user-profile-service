package com.example.user.profile.temporal.worker.impl;

import com.example.user.profile.temporal.TemporalClient;
import com.example.user.profile.temporal.worker.Worker;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public abstract class AbstractTemporalWorker implements Worker {
    private final Set<Class<?>> WORKFLOW_IMPL_CLASSES;
    private final String TASK_QUEUE;
    private final Set<Object> ACTIVITY_IMPLEMENTATIONS;
    private final Map<Class<?>, WorkflowOptions> WORKFLOW_OPTIONS_MAP;
    protected final TemporalClient temporalClient;

    @Override
    public TemporalClient getTemporalClient() {
        return temporalClient;
    }

    @Override
    public String getTaskQueue() {
        return TASK_QUEUE;
    }

    @Override
    public Set<Class<?>> getWorkflowImplementationTypes() {
        return WORKFLOW_IMPL_CLASSES;
    }

    @Override
    public Set<Object> getActivityImplementations() {
        return ACTIVITY_IMPLEMENTATIONS;
    }

    @Override
    public <T> T newWorkflow(Class<T> cls) {
        return temporalClient.newWorkflow(cls, WORKFLOW_OPTIONS_MAP.get(cls));
    }

}

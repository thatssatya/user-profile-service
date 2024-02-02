package com.example.user.profile.temporal.worker;

import com.example.user.profile.temporal.TemporalClient;

import java.util.Set;

public interface Worker {
    TemporalClient getTemporalClient();

    String getTaskQueue();

    Set<Class<?>> getWorkflowImplementationTypes();

    Set<Object> getActivityImplementations();

    <T> T newWorkflow(Class<T> cls);
}

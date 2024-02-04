package com.example.user.profile.library.temporal.worker;

import com.example.user.profile.library.temporal.activity.TemporalActivity;
import com.example.user.profile.library.temporal.workflow.TemporalWorkflow;

import java.util.Set;

public interface Worker {
    String getTaskQueue();

    Set<Class<? extends TemporalWorkflow>> getWorkflowImplementationTypes();

    Set<? extends TemporalActivity> getActivityImplementations();

    <T extends TemporalWorkflow> T newWorkflow(Class<T> cls);
}

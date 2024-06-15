package com.samsepiol.bom.library.temporal.worker;

import com.samsepiol.bom.library.temporal.activity.TemporalActivity;
import com.samsepiol.bom.library.temporal.workflow.TemporalWorkflow;

import java.util.Set;

public interface Worker {
    String getTaskQueue();

    Set<Class<? extends TemporalWorkflow>> getWorkflowImplementationTypes();

    Set<? extends TemporalActivity> getActivityImplementations();

    <T extends TemporalWorkflow> T newWorkflow(Class<T> cls);
}

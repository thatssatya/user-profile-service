package com.example.user.profile.temporal.impl;

import com.example.user.profile.temporal.TemporalClient;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.stereotype.Component;

@Component
public class BaseTemporalClient implements TemporalClient {
    private static final WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newLocalServiceStubs();
    private static final WorkflowClient workflowClient = WorkflowClient.newInstance(serviceStubs);

    @Override
    public WorkflowClient getInstance() {
        return workflowClient;
    }

    @Override
    public <T> T newWorkflow(Class<T> cls, WorkflowOptions options) {
        return workflowClient.newWorkflowStub(cls, options);
    }
}

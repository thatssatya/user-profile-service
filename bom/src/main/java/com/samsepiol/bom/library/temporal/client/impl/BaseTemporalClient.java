package com.samsepiol.bom.library.temporal.client.impl;

import com.samsepiol.bom.library.temporal.client.TemporalClient;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseTemporalClient implements TemporalClient {

    private final WorkflowClient workflowClient;

    @Override
    public WorkflowClient getInstance() {
        return workflowClient;
    }

    @Override
    public <T> T newWorkflow(Class<T> cls, WorkflowOptions options) {
        return workflowClient.newWorkflowStub(cls, options);
    }
}

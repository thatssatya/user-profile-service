package com.example.user.profile.library.temporal.client;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;

public interface TemporalClient {
    WorkflowClient getInstance();

    <T> T newWorkflow(Class<T> cls, WorkflowOptions options);
}

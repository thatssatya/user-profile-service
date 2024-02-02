package com.example.user.profile.temporal.worker.impl;

import com.example.user.profile.constants.TaskQueue;
import com.example.user.profile.service.UserService;
import com.example.user.profile.temporal.TemporalClient;
import com.example.user.profile.temporal.workflow.CreateUserWorkflow;
import com.example.user.profile.temporal.workflow.impl.BaseCreateUserWorkflow;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Component
public class UserServiceWorker extends AbstractTemporalWorker {

    public UserServiceWorker(TemporalClient temporalClient, UserService userService) {
        super(Set.of(BaseCreateUserWorkflow.class),
                TaskQueue.CREATE_USER,
                Set.of(userService),
                Map.of(CreateUserWorkflow.class, WorkflowOptions.newBuilder()
                        .setTaskQueue(TaskQueue.CREATE_USER)
                        .setWorkflowId(String.format("CREATE_USER_WORKFLOW-%s", UUID.randomUUID()))
                        .build()),
                temporalClient);
    }

}

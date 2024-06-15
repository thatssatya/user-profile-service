package com.samsepiol.userprofile.temporal.worker;

import com.samsepiol.bom.library.temporal.client.TemporalClient;
import com.samsepiol.bom.library.temporal.worker.impl.AbstractTemporalWorker;
import com.samsepiol.userprofile.constants.TaskQueue;
import com.samsepiol.userprofile.service.UserService;
import com.samsepiol.userprofile.temporal.workflow.impl.BaseCreateUserWorkflow;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserServiceWorker extends AbstractTemporalWorker {

    public UserServiceWorker(TemporalClient temporalClient, UserService userService) {
        super(BaseCreateUserWorkflow.class,
                TaskQueue.CREATE_USER,
                userService,
                WorkflowOptions.newBuilder()
                        .setWorkflowId(String.format("CREATE_USER_WORKFLOW-%s", UUID.randomUUID()))
                        .build(),
                temporalClient);
    }

}

package com.example.user.profile.temporal.workflow;

import com.example.user.profile.dao.entity.User;
import com.example.user.profile.service.request.CreateUserServiceRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CreateUserWorkflow {

    @WorkflowMethod
    User create(CreateUserServiceRequest serviceRequest);
}

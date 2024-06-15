package com.samsepiol.userprofile.temporal.workflow;

import com.samsepiol.bom.library.temporal.workflow.TemporalWorkflow;
import com.samsepiol.userprofile.dao.entity.User;
import com.samsepiol.userprofile.service.request.CreateUserServiceRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CreateUserWorkflow extends TemporalWorkflow {

    @WorkflowMethod
    User create(CreateUserServiceRequest serviceRequest);
}

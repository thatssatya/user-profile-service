package com.samsepiol.userprofile.service;

import com.samsepiol.userprofile.dao.entity.User;
import com.samsepiol.bom.library.temporal.activity.TemporalActivity;
import com.samsepiol.userprofile.service.request.CreateUserServiceRequest;
import com.samsepiol.userprofile.service.request.UserServiceRequest;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface UserService extends TemporalActivity {

    User getUser(UserServiceRequest serviceRequest);

    void createUser(CreateUserServiceRequest serviceRequest);
}

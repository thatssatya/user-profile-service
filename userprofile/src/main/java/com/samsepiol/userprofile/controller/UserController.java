package com.samsepiol.userprofile.controller;

import com.samsepiol.bom.library.temporal.TemporalWorkflowFactory;
import com.samsepiol.userprofile.model.request.CreateUserRequest;
import com.samsepiol.userprofile.model.response.UserResponse;
import com.samsepiol.userprofile.service.UserService;
import com.samsepiol.userprofile.temporal.workflow.CreateUserWorkflow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.samsepiol.userprofile.controller.mapper.UserResponseMapper.USER_RESPONSE_MAPPER;
import static com.samsepiol.userprofile.controller.mapper.UserServiceRequestMapper.USER_SERVICE_REQUEST_MAPPER;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TemporalWorkflowFactory temporalWorkflowFactory;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@NotBlank @PathVariable("userId") String userId) {
        var serviceRequest = USER_SERVICE_REQUEST_MAPPER.from(userId);
        var user = userService.getUser(serviceRequest);

        return ResponseEntity
                .ok()
                .body(USER_RESPONSE_MAPPER.from(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@NotNull @Valid @RequestBody CreateUserRequest request) {
        var serviceRequest = USER_SERVICE_REQUEST_MAPPER.from(request);

        var user = createUserWorkflow().create(serviceRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(USER_RESPONSE_MAPPER.from(user));
    }

    private CreateUserWorkflow createUserWorkflow() {
        return temporalWorkflowFactory.newWorkflow(CreateUserWorkflow.class);
    }
}

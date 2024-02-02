package com.example.user.profile.controller;

import com.example.user.profile.model.request.CreateUserRequest;
import com.example.user.profile.model.response.UserResponse;
import com.example.user.profile.service.UserService;
import com.example.user.profile.temporal.worker.factory.TemporalWorkerFactory;
import com.example.user.profile.temporal.workflow.CreateUserWorkflow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.example.user.profile.controller.mapper.UserResponseMapper.USER_RESPONSE_MAPPER;
import static com.example.user.profile.controller.mapper.UserServiceRequestMapper.USER_SERVICE_REQUEST_MAPPER;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TemporalWorkerFactory temporalWorkerFactory;

    private CreateUserWorkflow createUserWorkflow() {
        return temporalWorkerFactory.getWorker("userServiceWorker").newWorkflow(CreateUserWorkflow.class);
    }

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
}

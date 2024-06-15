package com.samsepiol.userprofile.service.request;

import com.samsepiol.userprofile.model.DOB;
import com.samsepiol.userprofile.model.validation.ValidDOB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CreateUserServiceRequest extends UserServiceRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final String nationalId;
    @ValidDOB
    private final DOB dob;
}

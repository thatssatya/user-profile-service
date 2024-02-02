package com.example.user.profile.model.request;

import com.example.user.profile.model.DOB;
import com.example.user.profile.model.validation.ValidDOB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest implements Serializable {

    @NotBlank
    private String name;
    @NotBlank
    private String nationalId;
    @NotNull
    @ValidDOB
    private DOB dob;
}

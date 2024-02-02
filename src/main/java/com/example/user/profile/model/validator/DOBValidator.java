package com.example.user.profile.model.validator;

import com.example.user.profile.model.DOB;
import com.example.user.profile.model.validation.ValidDOB;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DOBValidator implements ConstraintValidator<ValidDOB, DOB> {
    @Override
    public void initialize(ValidDOB constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(DOB dob, ConstraintValidatorContext constraintValidatorContext) {
        return dob.isValid();
    }
}

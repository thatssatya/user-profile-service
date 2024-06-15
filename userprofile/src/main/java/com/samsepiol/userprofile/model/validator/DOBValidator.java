package com.samsepiol.userprofile.model.validator;

import com.samsepiol.userprofile.model.DOB;
import com.samsepiol.userprofile.model.validation.ValidDOB;

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

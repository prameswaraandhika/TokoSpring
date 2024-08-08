package com.tokospring.admin.validators;

import com.tokospring.admin.dto.UserDto;
import com.tokospring.common.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordEqualValidator implements ConstraintValidator<PasswordEqualConstrain, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var user = (UserDto) value;
        if (user.getPassword() == null || user.getConfirmPassword() == null) {
            return false;  // or return true if you want to allow null values
        }

        boolean isValid = user.getPassword().equals(user.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

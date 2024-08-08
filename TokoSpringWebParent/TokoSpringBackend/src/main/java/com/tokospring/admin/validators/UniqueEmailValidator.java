package com.tokospring.admin.validators;

import com.tokospring.admin.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isExisted = userRepository.existsByEmail(value);
        if (isExisted){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email has been registered")
                    .addPropertyNode("emailUser")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

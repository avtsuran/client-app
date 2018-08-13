package partizan.global.service;

import partizan.global.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String MESSAGE = "message";


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME, MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PHONE, MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, EMAIL, MESSAGE);
    }
}

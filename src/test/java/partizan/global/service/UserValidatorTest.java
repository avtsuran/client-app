package partizan.global.service;

import partizan.global.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

public class UserValidatorTest {

    private static final String USER = "user";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String VALID_NAME = "John Doe";
    private static final String VALID_PHONE = "38099001100";
    private static final String INVALID_EMAIL = "";

    private User user;
    private Errors errors;
    private UserValidator validator;

    @Before
    public void setup() {
        validator = new UserValidator();
        user = createUser();
        errors = new BeanPropertyBindingResult(user, USER);
    }

    @Test
    public void shouldDiscardWhenUserIsInvalid() {
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
        assertNull(errors.getFieldError(NAME));
        assertNull(errors.getFieldError(PHONE));
        assertNotNull(errors.getFieldError(EMAIL));
    }

    private User createUser() {
        User user = new User();
        user.setName(VALID_NAME);
        user.setPhone(VALID_PHONE);
        user.setEmail(INVALID_EMAIL);
        return user;
    }
}

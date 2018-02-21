package blog.core.registration.validation.validators;


import com.google.common.base.Joiner;
import blog.core.registration.validation.annotations.ValidPassword;
import blog.model.service.UserServiceImpl;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    // Fields
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void initialize(ValidPassword validPassword) {}

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        final PasswordValidator passwordValidator
                = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30)
                , new UppercaseCharacterRule(1)
                , new DigitCharacterRule(1)
//                , new SpecialCharacterRule(1)
                , new WhitespaceRule()));

        final RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(Joiner.on("\n").join(passwordValidator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}

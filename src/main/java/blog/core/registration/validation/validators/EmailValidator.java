package blog.core.registration.validation.validators;

import blog.core.registration.validation.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    // Fields
    private Pattern pattern;
    private Matcher matcher;
//    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_PATTERN =
        "^[a-zA-Z0-9]{1,}"
        + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*"
        + "@"
        + "[a-zA-Z0-9]{1,}"
        + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*"
        + "\\.[a-zA-Z]{2,}$";

    @Override
    public void initialize(ValidEmail validEmail) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return (validateEmail(email));
    }

    private boolean validateEmail(final String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

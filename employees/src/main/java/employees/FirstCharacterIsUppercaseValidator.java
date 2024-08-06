package employees;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FirstCharacterIsUppercaseValidator implements ConstraintValidator<FirstCharacterIsUppercase, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && Character.isUpperCase(value.charAt(0));
    }
}

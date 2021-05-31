package oi.github.daylanbueno.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, Collection> {
    @Override
    public boolean isValid(Collection list,
                           ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize( NotEmptyList constraintAnnotation ) {
    }
}

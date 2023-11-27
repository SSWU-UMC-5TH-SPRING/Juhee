package umc.spring.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.PageLessNull;

@Component
@RequiredArgsConstructor
public class PageLessValidator implements ConstraintValidator<PageLessNull, Integer> {
    @Override
    public void initialize(PageLessNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value <= 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_LESS_NULL.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}

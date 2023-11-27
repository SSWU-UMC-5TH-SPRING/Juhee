package umc.spring.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import umc.spring.validation.validator.ExistUserMissionValidator;

@Documented
@Constraint(validatedBy = ExistUserMissionValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUserMission {
    String message() default "해당하는 미션이 없습니.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

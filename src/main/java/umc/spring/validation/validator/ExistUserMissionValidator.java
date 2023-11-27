package umc.spring.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.UserMission;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ExistUserMission;

@Component
@RequiredArgsConstructor
public class ExistUserMissionValidator implements ConstraintValidator<ExistUserMission, Long> {

    private final MissionQueryService missionQueryService;
    @Override
    public void initialize(ExistUserMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        UserMission userMission = missionQueryService.findUserMission(value);

        if (userMission == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

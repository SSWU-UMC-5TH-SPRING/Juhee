package umc.spring.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.UserMission;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.StatusMission;

@Component
@RequiredArgsConstructor
public class MissionStatusValidator implements ConstraintValidator<StatusMission, Long> {

    private final MissionQueryService missionQueryService;
    @Override
    public void initialize(StatusMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        UserMission userMission = missionQueryService.findUserMission(value);
        if (userMission.getStatus() == UserMissionStatus.INPROGRESS) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.INPROGRESS_MISSION.toString())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

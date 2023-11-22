package umc.spring.web.dto.mission;

import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import lombok.Getter;

public class MissionRequestDTO {
    @Getter
    public static class AddMissionDto{
        @Future
        LocalDate deadline;
        @NotNull
        Integer price;
        @NotNull
        Integer rewardPoints;
    }
}

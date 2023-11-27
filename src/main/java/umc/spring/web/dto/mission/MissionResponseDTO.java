package umc.spring.web.dto.mission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddMissionResultDTO{
        Long missionIdx;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListResultDTO {
        Long missionIdx;
        Long storeIdx;
        String name; // 가게 이름
        LocalDate deadline;
        Integer price;
        Integer rewardPoint;
    }
}

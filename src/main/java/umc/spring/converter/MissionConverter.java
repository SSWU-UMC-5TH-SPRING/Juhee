package umc.spring.converter;

import java.time.LocalDateTime;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

public class MissionConverter {
    public static MissionResponseDTO.AddMissionResultDTO toAddResultDTO(Mission mission) {
        return MissionResponseDTO.AddMissionResultDTO.builder()
                .missionIdx(mission.getMissionIdx())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.AddMissionDto request, Store store) {
        return Mission.builder()
                .deadline(request.getDeadline())
                .price(request.getPrice())
                .rewardPoints(request.getRewardPoints())
                .store(store)
                .build();
    }
}

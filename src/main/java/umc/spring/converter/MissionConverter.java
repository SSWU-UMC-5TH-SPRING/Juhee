package umc.spring.converter;

import java.time.LocalDateTime;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.UserMission;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;
import umc.spring.web.dto.mission.userMission.UserMissionResponseDTO;

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

    public static UserMissionResponseDTO.ChallengeMissionResultDTO toChallengeResultDTO(UserMission userMission) {
        return UserMissionResponseDTO.ChallengeMissionResultDTO.builder()
                .userMissionIdx(userMission.getUserMissionIdx())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UserMission toUserMission(Mission mission, User user) {
        return UserMission.builder()
                .mission(mission)
                .user(user)
                .status(UserMissionStatus.INPROGRESS)
                .build();
    }
}

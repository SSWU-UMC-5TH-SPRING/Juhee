package umc.spring.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
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

    public static List<MissionResponseDTO.MissionListResultDTO> getMissionResultListDTO(Page<Mission> missionList) {
        return missionList.stream()
                .map(mission -> MissionResponseDTO.MissionListResultDTO.builder()
                        .missionIdx(mission.getMissionIdx())
                        .rewardPoint(mission.getRewardPoints())
                        .price(mission.getPrice())
                        .deadline(mission.getDeadline())
                        .storeIdx(mission.getStore().getStoreIdx())
                        .name(mission.getStore().getName())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<MissionResponseDTO.InisProgressMissionDTO> getInisProgressMissionListDTO(Page<UserMission> missionList) {
        return missionList.stream()
                .map(mission -> MissionResponseDTO.InisProgressMissionDTO.builder()
                        .missionIdx(mission.getUserMissionIdx())
                        .storeIdx(mission.getMission().getStore().getStoreIdx())
                        .name(mission.getMission().getStore().getName())
                        .price(mission.getMission().getPrice())
                        .status(mission.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}

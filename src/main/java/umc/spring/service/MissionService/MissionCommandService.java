package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.domain.UserMission;
import umc.spring.web.dto.mission.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMission(Long storeIdx, MissionRequestDTO.AddMissionDto request);
    UserMission challengeMission(Long missionIdx, Long userIdx);
    UserMission changeUserMissionStatus(Long userMissionIdx);
}

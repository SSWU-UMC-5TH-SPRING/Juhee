package umc.spring.service.MissionService;

import umc.spring.domain.UserMission;

public interface MissionQueryService {
    UserMission findUserMission(Long userMissionIdx);
}

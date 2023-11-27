package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.UserMission;

public interface MissionQueryService {
    UserMission findUserMission(Long userMissionIdx);
    Page<Mission> getMissionList(Long storeIdx, Integer page);
    Page<UserMission> getInisProgressMissionList(Long userIdx, Integer page);
}

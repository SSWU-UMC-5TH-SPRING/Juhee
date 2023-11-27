package umc.spring.service.MissionService;

import java.util.List;
import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.UserMission;

public interface MissionQueryService {
    UserMission findUserMission(Long userMissionIdx);
    Page<Mission> getMissionList(Long storeIdx, Integer page);
}

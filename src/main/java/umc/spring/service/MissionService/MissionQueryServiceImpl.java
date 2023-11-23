package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.UserMission;
import umc.spring.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final UserMissionRepository userMissionRepository;

    @Override
    public UserMission findUserMission(Long userMissionIdx) {
        return userMissionRepository.getReferenceById(userMissionIdx);
    }
}

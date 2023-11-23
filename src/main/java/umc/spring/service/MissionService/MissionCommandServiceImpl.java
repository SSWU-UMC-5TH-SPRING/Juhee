package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.UserMission;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.repository.UserMissionRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.mission.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public Mission addMission(Long storeIdx, MissionRequestDTO.AddMissionDto request) {
        Store store = storeRepository.getReferenceById(storeIdx);
        Mission mission = MissionConverter.toMission(request, store);

        mission.setStore(storeRepository.getReferenceById(storeIdx));

        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public UserMission challengeMission(Long missionIdx, Long userIdx) {
        Mission mission = missionRepository.getReferenceById(missionIdx);
        User user = userRepository.getReferenceById(userIdx);

        UserMission userMission = userMissionRepository.findByMissionAndUser(mission, user);

        if (userMission != null) {
            MissionConverter.toUserMission(mission, user);
            userMission.setStatus(UserMissionStatus.INPROGRESS);
        }
        return userMission;
    }
}
